package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import kotlin.math.abs

class DiagonalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        if(!board.isInBounds(movement.finalpos)){
            return false
        }
        val difRow : Int = abs(movement.initpos.row - movement.finalpos.row)
        val difcol : Int = abs(movement.initpos.column - movement.finalpos.column)
        return difRow == difcol
    }
}