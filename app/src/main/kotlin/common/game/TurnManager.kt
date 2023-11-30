package common.game

import common.piece.Color

interface TurnManager {
    fun nextTurn(currentPlayer: Color): TurnManager
    fun getCurrentPlayer(): Color
}