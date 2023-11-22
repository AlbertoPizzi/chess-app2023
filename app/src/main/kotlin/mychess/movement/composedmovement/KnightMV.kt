package mychess.movement.composedmovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.concretemovement.ColorMV
import common.movementvalidators.concretemovement.EatMV
import common.movementvalidators.concretemovement.PositionIsFreeMV
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class KnightMV : MovementValidator {
    private val colorMV : MovementValidator = ColorMV()
    private val positionIsFreeMV : MovementValidator = PositionIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val difRow : Int = abs(movement.initpos.row - movement.finalpos.row )
        val difCol : Int = abs(movement.initpos.column - movement.finalpos.column)
        if ((difRow == 1 && difCol == 2) || (difRow == 2 && difCol == 1)){
            if(positionIsFreeMV.validateMovement(board , movement) is SuccessfulResult)
                return SuccessfulResult("Is valid movement")
            else if(positionIsFreeMV.validateMovement(board , movement) is FailureResult
                && eatMV.validateMovement(board , movement) is SuccessfulResult)
                return SuccessfulResult("Is valid movement")
        }
        return FailureResult("Not valid movement")
        }
    }
