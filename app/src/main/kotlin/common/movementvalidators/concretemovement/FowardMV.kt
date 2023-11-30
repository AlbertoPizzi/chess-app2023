package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class FowardMV : MovementValidator {
    private val verticalMV :MovementValidator = VerticalMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        when(board.getPieceByPosition(movement.initpos)!!.pieceColor){
            Color.WHITE -> {
                if(verticalMV.validateMovement(board , movement) is SuccessfulResult
                    && movement.finalpos.row > movement.initpos.row){
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("invalid movment")
            }
            Color.BLACK -> {
                if(verticalMV.validateMovement(board ,movement ) is SuccessfulResult
                    && movement.finalpos.row < movement.initpos.row){
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("Invalid movement")
            }
        }
    }
}