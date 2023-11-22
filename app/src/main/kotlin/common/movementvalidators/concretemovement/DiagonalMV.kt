package common.movementvalidators.concretemovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
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