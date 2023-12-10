package edu.austral.dissis.chess.mychess.promotion

import edu.austral.dissis.chess.common.promotion.PromotionStrategy
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.PieceType
import edu.austral.dissis.chess.common.rules.Game

class ChessPromotionStrategy : PromotionStrategy {
    override fun promote(game : Game): Game { //Busca si hay algun Peon en los limites del board
        val gameState = game.getGameState()
        val board = gameState.board
        val pawns = board.getPositionMap()
            .filter { it.value.pieceColor == gameState.turnManager.getCurrentPlayer() && it.value.type == PieceType.PAWN }
        val toRow = if (gameState.turnManager.getCurrentPlayer() == Color.WHITE) board.getRowSize() else 1
        for ((position, piece) in pawns) {
            val pawnPosition = position
            if (comparePositionToBoardLimit(pawnPosition, toRow)) {
                val auxChessPieceFactory = ChessPieceFactory
                val newQueen = auxChessPieceFactory.buildQueen(piece.id, gameState.turnManager.getCurrentPlayer())
                var newMutableMap = gameState.getPositionMap().toMutableMap()
                newMutableMap.replace(pawnPosition, newQueen)
                return game.copy(state =  gameState.copy(board = BoardFactory.updateBoard(newMutableMap.toMap(), board)))
            }
        }
        return game

    }

    private fun comparePositionToBoardLimit(position: Position, row: Int): Boolean {
        return position.row == row
    }

}