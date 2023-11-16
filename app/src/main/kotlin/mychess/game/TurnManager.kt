package mychess.game

import commons.piece.Color

interface TurnManager {
    fun nextTurn(currentPlayer : Color) : TurnManager
    fun getCurrentPlayer() : Color
}