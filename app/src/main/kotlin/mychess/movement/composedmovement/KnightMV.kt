package mychess.movement.composedmovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.movementvalidators.concretemovement.ColorMV
import commons.movementvalidators.concretemovement.EatMV
import commons.movementvalidators.concretemovement.PositionIsFreeMV
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult
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
                && eatMV.validateMovement(board , movement) is SuccessfulResult
            )
                return SuccessfulResult("Is valid movement")
        }
        return FailureResult("Not valid movement")
        }
    }
