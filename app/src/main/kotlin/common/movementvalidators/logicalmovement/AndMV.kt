package common.movementvalidators.logicalmovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class AndMV(val mvList : List<MovementValidator>) : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        for (mv in mvList) {
            val auxResult = mv.validateMovement(board, movement)
            if (auxResult is FailureResult) {
                return auxResult
            }
        }
        return SuccessfulResult("It is a valid move!")
    }
}