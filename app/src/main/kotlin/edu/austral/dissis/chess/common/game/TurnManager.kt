package edu.austral.dissis.chess.common.game

import edu.austral.dissis.chess.common.piece.Color

interface TurnManager {
    fun nextTurn(): TurnManager
    fun getCurrentPlayer(): Color
}