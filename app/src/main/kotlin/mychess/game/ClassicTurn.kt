package mychess.game

import mychess.piece.Color

class ClassicTurn(private val color : Color) : TurnManager{
    override fun nextTurn(currentPlayer: Color): TurnManager {
        return ClassicTurn(if(currentPlayer == Color.WHITE) Color.BLACK else Color.WHITE)
    }

    override fun getCurrentPlayer(): Color {
        return color
    }

}