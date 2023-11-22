package mychess.movement.composedmovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.concretemovement.*
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class BishopMV : MovementValidator {
    private val diagonalMV : MovementValidator = DiagonalMV ()
    private val freePath : MovementValidator = PathIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    private val colorMV : MovementValidator = ColorMV()
    private val positionIsFreeMV : MovementValidator = PositionIsFreeMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val target : Piece? = board.getPositionMap().get(movement.finalpos)
            if(diagonalMV.validateMovement(board , movement) is SuccessfulResult
            ){
                if(positionIsFreeMV.validateMovement(board, movement) is SuccessfulResult
                    && freePath.validateMovement(board , movement) is SuccessfulResult)
                return SuccessfulResult("valid movement")

                if(target != null && eatMV.validateMovement(board , movement) is FailureResult){
                    return FailureResult("Not valid movement")
                }
                else if (target != null && eatMV.validateMovement(board, movement) is SuccessfulResult
                    && freePath.validateMovement(board , movement) is SuccessfulResult){
                    return SuccessfulResult("valid movement")
                }
            }

        return FailureResult("Invalid movement")
    }
}