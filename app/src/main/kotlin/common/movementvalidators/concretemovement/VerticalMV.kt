package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class VerticalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if ((board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)) {
            return SuccessfulResult("Its a vertical Movement!")
        }
        return FailureResult("It is not a Vertical Movement!")
    }
}