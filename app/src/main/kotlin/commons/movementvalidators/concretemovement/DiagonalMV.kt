package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult
import kotlin.math.abs

class DiagonalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(!board.isInBounds(movement.finalpos)){
            return FailureResult("Movement is out of bounds!")
        }
        val difRow : Int = abs(movement.initpos.row - movement.finalpos.row)
        val difcol : Int = abs(movement.initpos.column - movement.finalpos.column)
        if( difRow == difcol){
            return SuccessfulResult("It is a diagonal Movement!")
        }
        return FailureResult("It isn't a Diagonal Movement!")
    }
}