package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class JumpMV(private val x : Int , private val y : Int) : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if (isJumping(movement)) {
            return SuccessfulResult("Movement Successful ")
        }
        return FailureResult("Piece is not moving correctly")
    }

    private fun isJumping(movement: Movement): Boolean {
        val auxX = abs(movement.finalpos.column - movement.initpos.column)
        val auxY = abs(movement.finalpos.row - movement.initpos.row)
        return (auxX == x && auxY == y) || (auxX == y && auxY == x)
    }
}