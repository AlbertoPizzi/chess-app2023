package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class VerticalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if((board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)){
            return SuccessfulResult("Its a vertical Movement!")
        }
        return FailureResult("It is not a Vertical Movement!")
    }
}