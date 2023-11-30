package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class MaxMV(private val max : Int , private val pieceID : String): MovementValidator{
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
    return TODO("you gotta do this")
    }

}