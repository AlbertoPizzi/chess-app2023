package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class VerticalMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        if ((board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)) {
            return SuccessfulResult("Its a vertical Movement!")
        }
        return FailureResult("It is not a Vertical Movement!")
    }
}