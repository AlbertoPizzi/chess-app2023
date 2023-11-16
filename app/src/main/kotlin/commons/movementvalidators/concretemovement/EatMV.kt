package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class EatMV : MovementValidator {
    private val colorCheck : MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(colorCheck.validateMovement(board, movement) is FailureResult){
            return SuccessfulResult("This piece can eat!")
        }
        else return FailureResult("Cannot eat your teammate!")
    }
}