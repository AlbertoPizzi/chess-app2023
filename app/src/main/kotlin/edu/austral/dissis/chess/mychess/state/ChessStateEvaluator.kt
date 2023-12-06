package edu.austral.dissis.chess.mychess.state

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluator
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.piece.PieceType
import edu.austral.dissis.chess.common.result.SuccessfulResult

class ChessStateEvaluator: StateEvaluator {
    override fun validate(gameState: GameState): StateEvaluatorResult {
        if(isCheckMate(gameState)) {
            when(gameState.turnManager.getCurrentPlayer()) {
                Color.WHITE -> return WinStateResult(Color.BLACK)
                Color.BLACK -> return WinStateResult(Color.WHITE)
            }
        }
        else return InProgressStateResult()
    }

    fun isCheckMate(gameState: GameState): Boolean{
        val turnManager = gameState.turnManager
        val newGameState = gameState.copy(turnManager.nextTurn())
        if(isKingThreaten(gameState)){
            val pieceList = newGameState.history.boardHistory.last().getPositionMap().entries.filter { it.value.pieceColor == newGameState.turnManager.getCurrentPlayer() }
            for (piece in pieceList) {
                if (!chessPieceHasAnyValidMovement(piece.value , gameState)) {
                    return true
                }
            }
            return false
        }
        return false
    }

    fun threatsToTheKing(gameState: GameState): List<Position>{
        val board = gameState.board
        val color = gameState.turnManager.getCurrentPlayer()
        var listOfPosition = mutableListOf<Position>()
        val kingPosition = board.getPositionMap().entries.find { it.value.type == PieceType.KING && it.value.pieceColor == color }!!.key
        val enemyPieces = board.getPositionMap().entries.filter { it.value.pieceColor !== color }
        for (enemyPiece in enemyPieces) {
            val enemyPiecePosition = enemyPiece.key
            val enemyPieceMovement = Movement(kingPosition, enemyPiecePosition)
            if (enemyPiece.value.mv[0].validateMovement( gameState = gameState, enemyPieceMovement,) is SuccessfulResult) {
                listOfPosition.add(enemyPiecePosition)
            }
        }
        val immutableList = listOfPosition.toList()
        return immutableList
    }
    fun isKingThreaten(gameState: GameState): Boolean {
        return threatsToTheKing(gameState).isNotEmpty()
    }

    fun chessPieceHasAnyValidMovement(piece: Piece , gameState: GameState): Boolean {
        val board = gameState.board
        val initPos = board.getPositionByPiece(piece)!!
        for(i in 1 .. board.getColSize()){
            for (j in 1 .. board.getRowSize()){
                val finalPos = Position(i,j)
                val auxMovement = Movement(initPos, finalPos)
                if(piece.mv[0].validateMovement(gameState = gameState, auxMovement) is SuccessfulResult){
                    return false
                }
                return true
            }
        }
        return false
    }


}