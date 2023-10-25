package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class VerticalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if((board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)){
            return SuccessfulResult("Its a vertical Movement!")
        }
        return FailureResult("It is not a Vertical Movement!")
    }
}