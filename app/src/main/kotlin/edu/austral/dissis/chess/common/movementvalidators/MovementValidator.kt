package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.result.ResultValidator

interface MovementValidator {
    fun validateMovement(gameState: GameState, movement: Movement): ResultValidator
}