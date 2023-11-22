package common.movementvalidators.concretemovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class HorizontalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        return if(board.isInBounds(movement.finalpos) && (movement.initpos.row == movement.finalpos.row)){
            SuccessfulResult("It is a horizontal Movement")
        } else FailureResult("It is not a horizontal movement!")
    }
}