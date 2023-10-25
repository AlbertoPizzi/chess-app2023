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
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(verticalMV.validateMovement(board , movement) is SuccessfulResult ||
                horizontalMV.validateMovement(board , movement) is SuccessfulResult){
            if(board.isInBounds(movement.finalpos) && freeMV.validateMovement(board, movement) is SuccessfulResult){
                return SuccessfulResult("It's a valid Move")
            }
        }
        return FailureResult("It's not a valid move")
    }
}