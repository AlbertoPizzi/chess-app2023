package common.movementvalidators.concretemovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class EatMV : MovementValidator {
    private val colorCheck: MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if (colorCheck.validateMovement(board, movement) is FailureResult) {
            return SuccessfulResult("This piece can eat!")
        } else return FailureResult("Cannot eat your teammate!")
    }
}