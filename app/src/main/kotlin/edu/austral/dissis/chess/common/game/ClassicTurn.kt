package edu.austral.dissis.chess.common.game

import edu.austral.dissis.chess.common.piece.Color

class ClassicTurn(private val currentPlayer: Color) : TurnManager {
    override fun nextTurn(): TurnManager {
        return ClassicTurn(if (currentPlayer == Color.WHITE) Color.BLACK else Color.WHITE)
    }

    override fun getCurrentPlayer(): Color {
        return currentPlayer
    }
}