package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class HorizontalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        return if(board.isInBounds(movement.finalpos) && (movement.initpos.row == movement.finalpos.row)){
            SuccessfulResult("It is a horizontal Movement")
        } else FailureResult("It is not a horizontal movement!")
    }
}