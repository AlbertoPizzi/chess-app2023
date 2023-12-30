package edu.austral.dissis.chess.common.game.turnmanagement

import edu.austral.dissis.chess.common.piece.Color

class ClassicTurn(private val currentPlayer: Color) : TurnManager {
    override fun nextTurn(): TurnManager {
//        return ClassicTurn(if (currentPlayer == Color.WHITE) Color.BLACK else Color.WHITE)
        return when(getCurrentPlayer() ){
            Color.WHITE -> ClassicTurn(currentPlayer = Color.BLACK)
            Color.BLACK -> ClassicTurn(currentPlayer = Color.WHITE)
        }
    }

    override fun getCurrentPlayer(): Color {
        return currentPlayer
    }
}