package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
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