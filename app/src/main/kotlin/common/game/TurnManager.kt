package common.game

import common.piece.Color

interface TurnManager {
    fun nextTurn(): TurnManager
    fun getCurrentPlayer(): Color
}