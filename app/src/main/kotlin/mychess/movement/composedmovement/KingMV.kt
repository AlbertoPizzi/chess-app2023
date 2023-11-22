package mychess.movement.composedmovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.concretemovement.*
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import java.lang.Math.abs

class KingMV : MovementValidator {
    private val diagonalMV: MovementValidator = DiagonalMV()
    private val horizontalMV: MovementValidator = HorizontalMV()
    private val verticalMV: MovementValidator = VerticalMV()
    private val eatMV: MovementValidator = EatMV()
    private val positionIsFreeMV: MovementValidator = PositionIsFreeMV()

    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if (diagonalMV.validateMovement(board, movement) is SuccessfulResult ||
            horizontalMV.validateMovement(board, movement) is SuccessfulResult ||
            verticalMV.validateMovement(board, movement) is SuccessfulResult
        ) {
            if (checkKingLimit(board, movement)) {
                if (positionIsFreeMV.validateMovement(board, movement) is SuccessfulResult) {
                    return SuccessfulResult("This is a valid move for a king!")
                } else if (positionIsFreeMV.validateMovement(board, movement) is FailureResult
                    && eatMV.validateMovement(board, movement) is SuccessfulResult
                ) {
                    return SuccessfulResult("This is a valid move for a king!")
                }
            }
        }
        return FailureResult("This is an invalid move for a king!")
    }

    fun checkKingLimit(board: Board, movement: Movement): Boolean {
        val difRow: Int = movement.initpos.row - movement.finalpos.row
        val difColumn: Int = movement.initpos.column - movement.finalpos.column
        return abs(difRow.toDouble()) <= 1 && abs(difColumn.toDouble()) <= 1
    }
}