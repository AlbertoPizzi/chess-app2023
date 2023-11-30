package common.game

import common.board.Board
import common.board.Position
import common.movementvalidators.Movement
import common.piece.PieceType
import common.piece.Color
import common.piece.Piece
import common.result.FailureResult
import common.result.SuccessfulResult

data class GameState(private val turnManager: TurnManager, private val boardHistory: List<Board>) {
    fun getTurnManager(): TurnManager {
        return turnManager
    }

    fun getBoardHistory(): List<Board> {
        return boardHistory
    }
    fun threatsToTheKing(color: Color): List<Position>{
        val board = boardHistory.last()
        var listOfPosition = mutableListOf<Position>()
        val kingPosition = board.getPositionMap().entries.find { it.value.type == PieceType.KING && it.value.pieceColor == color }!!.key
        val enemyPieces = board.getPositionMap().entries.filter { it.value.pieceColor !== color }
        for (enemyPiece in enemyPieces) {
            val enemyPiecePosition = enemyPiece.key
            val enemyPieceMovement = Movement(kingPosition, enemyPiecePosition)
            if (enemyPiece.value.movement.get(0).validateMovement( gameState = this, enemyPieceMovement,) is SuccessfulResult) {
                listOfPosition.add(enemyPiecePosition)
            }
        }
        val immutableList = listOfPosition.toList()
        return immutableList
    }
    fun isKingThreaten(color: Color): Boolean {
        return threatsToTheKing(color).isNotEmpty()
    }

    fun chessPieceHasAnyValidMovement(piece: Piece): Boolean {
        val board = boardHistory.last()
        val initPos = board.getPositionByPiece(piece)!!
        for(i in 1 .. board.getColSize()){
            for (j in 1 .. board.getRowSize()){
                val finalPos = Position(i,j)
                val auxMovement = Movement(initPos, finalPos)
                if(piece.movement[0].validateMovement(gameState = this, auxMovement) is SuccessfulResult){
                    return false
                }
                return true
            }
        }
        return false
    }


}