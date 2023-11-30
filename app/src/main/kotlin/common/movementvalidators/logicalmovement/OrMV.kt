package common.movementvalidators.logicalmovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class OrMV(private val mvList: List<MovementValidator>) : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        for (mv in mvList) {
            val auxResult = mv.validateMovement(gameState, movement)
            if (auxResult is SuccessfulResult) {
                return auxResult
            }
        }
        return FailureResult("It is not a valid move!")
    }
}