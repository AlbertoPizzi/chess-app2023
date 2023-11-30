package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.piece.Piece
import common.piece.PieceType
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class PromotionMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if (isPromotionable(board , movement)){
            return SuccessfulResult("Promotion!")
        }
        return FailureResult("Not promotionable!")
    }
    private fun isPromotionable(board: Board , movement: Movement) : Boolean{
        val pieceToPromote = board.getPieceByPosition(movement.initpos)!!
        if(pieceToPromote.type == PieceType.PAWN
            || pieceToPromote.type == PieceType.CHECKER){
            return when(pieceToPromote.pieceColor){
                Color.WHITE -> movement.finalpos.row == 8
                Color.BLACK -> movement.finalpos.row == 1
            }
        }
        return false
    }
}