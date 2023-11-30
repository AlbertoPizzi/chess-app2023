package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class EatMV : MovementValidator {
    private val colorCheck: MovementValidator = ColorMV()
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        if (board.getPositionMap().containsKey(movement.finalpos) && colorCheck.validateMovement(
                gameState,
                movement
            ) is FailureResult
        ) {
            return SuccessfulResult("This piece can eat!")
        } else return FailureResult("Cannot eat your teammate!")
    }
}