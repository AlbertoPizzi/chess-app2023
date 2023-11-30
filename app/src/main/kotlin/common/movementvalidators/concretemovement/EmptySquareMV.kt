package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class EmptySquareMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        if(board.getPositionMap().containsKey(movement.initpos)){
            return SuccessfulResult("There's a piece in the initial position")
        }
        else return FailureResult("There's no piece there")
    }
}