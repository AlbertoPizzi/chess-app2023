package common.movementvalidators

import common.game.GameState
import common.result.ResultValidator

interface MovementValidator {
    fun validateMovement(gameState: GameState, movement: Movement): ResultValidator
}