package mychess.movement.composedmovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.DiagonalMV
import mychess.movement.concretemovement.EatMV
import mychess.movement.concretemovement.HorizontalMV
import mychess.movement.concretemovement.VerticalMV
import mychess.piece.Piece
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
import java.lang.Math.E
import java.lang.Math.abs

class KingMV : MovementValidator {
    private val diagonalMV : MovementValidator = DiagonalMV()
    private val horizontalMV : MovementValidator = HorizontalMV()
    private val verticalMV : MovementValidator = VerticalMV()
    private val eatMV : MovementValidator = EatMV()


    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(diagonalMV.validateMovement(board , movement) is SuccessfulResult ||
            horizontalMV.validateMovement(board , movement) is SuccessfulResult ||
            verticalMV.validateMovement(board , movement) is SuccessfulResult) {
            if(checkKingLimit(board, movement)){
                return SuccessfulResult("This is a valid move for a king!")

            }
        }
        return FailureResult("This is an invalid move for a king!")
    }
    fun checkKingLimit(board: Board , movement: Movement): Boolean{
        val difRow : Int = movement.initpos.row - movement.finalpos.row
        val difColumn : Int = movement.initpos.column - movement.finalpos.column
        return abs(difRow.toDouble()) <= 1 && abs(difColumn.toDouble()) <= 1
    }
}