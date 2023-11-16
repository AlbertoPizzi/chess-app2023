package mychess.movement.composedmovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.movementvalidators.concretemovement.*
import commons.piece.Piece
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

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
                    && freePath.validateMovement(board , movement) is SuccessfulResult
                )
                return SuccessfulResult("valid movement")

                if(target != null && eatMV.validateMovement(board , movement) is FailureResult){
                    return FailureResult("Not valid movement")
                }
                else if (target != null && eatMV.validateMovement(board, movement) is SuccessfulResult
                    && freePath.validateMovement(board , movement) is SuccessfulResult
                ){
                    return SuccessfulResult("valid movement")
                }
            }

        return FailureResult("Invalid movement")
    }
}