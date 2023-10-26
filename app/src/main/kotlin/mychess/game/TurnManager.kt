package mychess.game

import mychess.piece.Color

interface TurnManager {
    fun nextTurn(currentPlayer : Color) : TurnManager
    fun getCurrentPlayer() : Color
}