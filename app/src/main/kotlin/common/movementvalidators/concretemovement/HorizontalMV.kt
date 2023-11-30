package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class HorizontalMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
//        val board = gameState.getBoardHistory().last()
        return if ((movement.initpos.row == movement.finalpos.row)) {
            SuccessfulResult("It is a horizontal Movement")
        } else FailureResult("It is not a horizontal movement!")
    }
}