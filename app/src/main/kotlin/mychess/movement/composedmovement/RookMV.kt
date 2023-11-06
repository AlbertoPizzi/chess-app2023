package mychess.movement.composedmovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.EatMV
import mychess.movement.concretemovement.HorizontalMV
import mychess.movement.concretemovement.PathIsFreeMV
import mychess.movement.concretemovement.VerticalMV
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class RookMV : MovementValidator {
    private val verticalMV : MovementValidator = VerticalMV()
    private val horizontalMV : MovementValidator = HorizontalMV()
    private val freeMV: MovementValidator = PathIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    private val positionIsFreeMV : MovementValidator = PathIsFreeMV()
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
            && eatMV.validateMovement(board, movement) is SuccessfulResult ){
            return SuccessfulResult("Valid movement")
        }
        return FailureResult("Invalid movement")
    }
}