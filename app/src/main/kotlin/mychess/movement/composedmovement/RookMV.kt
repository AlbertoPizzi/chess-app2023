package mychess.movement.composedmovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.movementvalidators.concretemovement.*
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class RookMV : MovementValidator {
    private val verticalMV : MovementValidator = VerticalMV()
    private val horizontalMV : MovementValidator = HorizontalMV()
    private val freeMV: MovementValidator = PathIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    private val positionIsFreeMV : MovementValidator = PositionIsFreeMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(positionIsFreeMV.validateMovement(board , movement) is SuccessfulResult){
            if(freeMV.validateMovement(board , movement) is SuccessfulResult){
                if(horizontalMV.validateMovement(board , movement) is SuccessfulResult){
                    return SuccessfulResult("Valid movement")
                }
                if(verticalMV.validateMovement(board , movement) is SuccessfulResult){
                    return SuccessfulResult("Valid movement")
                }
            }
        }
        if(positionIsFreeMV.validateMovement(board , movement) is FailureResult
            && eatMV.validateMovement(board, movement) is SuccessfulResult
            && freeMV.validateMovement(board , movement) is SuccessfulResult
        ){
            if(horizontalMV.validateMovement(board , movement) is SuccessfulResult
                || verticalMV.validateMovement(board, movement) is SuccessfulResult
            ){
                return SuccessfulResult("Valid movement")
            }
        }
        return FailureResult("Invalid movement")
    }
}