package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class HorizontalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(board.isInBounds(movement.finalpos) && (movement.initpos.row == movement.finalpos.row)){
            return SuccessfulResult("It is a horizontal Movement")
        }
        else return FailureResult("It is not a horizontal movement!")
    }
}