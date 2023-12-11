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
import edu.austral.dissis.chess.common.rules.Game
import edu.austral.dissis.chess.mychess.movement.composedmovement.NotInCheckMV


class ChessStateEvaluator : StateEvaluator {
    override fun validate(game: Game): StateEvaluatorResult {
        val gameState = game.getGameState()
        if (isCheckMate(game)) {
            when (gameState.turnManager.getCurrentPlayer()) {
                Color.WHITE -> return WinStateResult(Color.WHITE)
                Color.BLACK -> return WinStateResult(Color.BLACK)
            }
        } else return InProgressStateResult(game)

    }

    fun isCheckMate(game: Game): Boolean {
        val turnManager = game.getGameState().turnManager
        val newGameState = game.copy(state = game.getGameState().copy(turnManager = turnManager.nextTurn()))
        if (!isKingThreaten(newGameState))
            return false
        val pieceList =
            newGameState.getGameState().board.getPositionMap().entries.filter { it.value.pieceColor == newGameState.getGameState().getCurrentPlayer() }
        for (piece in pieceList) {
            if (chessPieceHasAnyValidMovement(piece.value, game)) {
                return false
            }
        }
        return true
    }

    fun threatsToTheKing(game: Game): List<Position> {
        val gameState = game.getGameState()
        val board = gameState.board
        val color = gameState.turnManager.getCurrentPlayer()
        var listOfPosition = mutableListOf<Position>()
        val kingPosition =
            board.getPositionMap().entries.find { it.value.type == PieceType.KING && it.value.pieceColor == color }!!.key
        val enemyPieces = board.getPositionMap().entries.filter { it.value.pieceColor != color }
        for (enemyPiece in enemyPieces) {
            val enemyPiecePosition = enemyPiece.key
            val enemyPieceMovement = Movement(enemyPiecePosition, kingPosition)
            if (enemyPiece.value.mv[0].validateMovement(
                    game = game,
                    enemyPieceMovement,
                ) is SuccessfulResult
            ) {
                listOfPosition.add(enemyPiecePosition)
            }
        }
        val immutableList = listOfPosition.toList()
        return immutableList
    }

    fun isKingThreaten(game: Game): Boolean {
        return threatsToTheKing(game).isNotEmpty()
    }


    fun chessPieceHasAnyValidMovement(piece: Piece, game: Game): Boolean {
        val gameState = game.getGameState()
        val board = gameState.board
        val initPos = board.getPositionByPiece(piece)!!
        for (i in 1..board.getColSize()) {
            for (j in 1..board.getRowSize()) {
                val finalPos = Position(i, j)
                val auxMovement = Movement(initPos, finalPos)
                if (piece.mv[0].validateMovement(game = game, auxMovement) is SuccessfulResult
                    && NotInCheckMV().validateMovement(game, auxMovement) is SuccessfulResult
                ) {
                    return true
                }
            }
        }
        return false
    }


}