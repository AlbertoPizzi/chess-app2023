package edu.austral.dissis.chess.checkers.promotion

import edu.austral.dissis.chess.common.PromotionStrategy
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.factory.PieceFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.PieceType

class CheckersPromotionStrategy : PromotionStrategy {
    override fun promote(gameState: GameState): GameState {
        val checkers = gameState.getPositionMap()
            .filter { it.value.pieceColor == gameState.getCurrentPlayer() && it.value.type.equals(PieceType.PAWN) }
        val toRow = if (gameState.getCurrentPlayer() == Color.WHITE) gameState.board.getRowSize() else 1
        for ((position, piece) in checkers) {
            val checkerPosition = position
            if (comparePositionToBoardLimit(checkerPosition, toRow)) {
                val newCrowned = PieceFactory.buildCrowned(piece.id, gameState.getCurrentPlayer())
                var newMutableMap = gameState.getPositionMap().toMutableMap()
                newMutableMap.replace(checkerPosition, newCrowned)
                return gameState.copy(board = BoardFactory.updateBoard(newMutableMap, gameState.board))
            }
        }
        return gameState
    }

    private fun comparePositionToBoardLimit(position: Position, row: Int): Boolean {
        return position.row == row
    }
}