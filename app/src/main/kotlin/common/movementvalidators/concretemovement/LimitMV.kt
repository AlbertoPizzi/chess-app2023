package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class LimitMV(private val limit: Int) : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val difCol = abs(movement.finalpos.column - movement.initpos.column)
        val difRow = abs(movement.finalpos.row - movement.initpos.row)
        if (difCol <= limit && difRow <= limit) {
            return SuccessfulResult("The movement is valid")
        }
        return FailureResult( "The movement exceeds its limit")
    }
}