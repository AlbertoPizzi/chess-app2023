package edu.austral.dissis.chess.common.game.turnmanagement

import edu.austral.dissis.chess.common.piece.Color

interface TurnManager {
    fun nextTurn(): TurnManager
    fun getCurrentPlayer(): Color
}