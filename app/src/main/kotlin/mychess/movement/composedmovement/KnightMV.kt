package mychess.movement.composedmovement

import mychess.board.Board
import mychess.board.Position
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.ColorMV
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
import kotlin.math.abs

class KnightMV : MovementValidator {
    private val colorMV : MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val difRow : Int = abs(movement.initpos.row - movement.finalpos.row )
        val difCol : Int = abs(movement.initpos.column - movement.finalpos.column)
        if ((difRow == 1 && difCol == 2) || (difRow == 2 && difCol == 1)){
                return SuccessfulResult("Is valid movement")
        }
        return FailureResult("Not valid movement")
        }
    }
