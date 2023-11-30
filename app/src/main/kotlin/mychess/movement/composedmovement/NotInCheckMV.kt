package mychess.movement.composedmovement

import common.board.Board
import common.board.Position
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class NotInCheckMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        when (board.getPieceByPosition(movement.initpos)?.pieceColor) {
            Color.WHITE -> {
                val designatedPosition = board.getPositionByID("KW")!!
                if (designatedPosition == movement.initpos) {
                    if (isPieceColorTargetingPosition(movement.finalpos, board, Color.BLACK)) {
                        return FailureResult("King is in check")
                    }
                } else if (isPieceColorTargetingPosition(designatedPosition, board, Color.BLACK)) {
                    return FailureResult("King is in check")
                }
                return SuccessfulResult("Valid movement")
            }
            Color.BLACK -> {
                val designatedPosition = board.getPositionByID("KB")!!
                if (designatedPosition == movement.initpos) {
                    if (isPieceColorTargetingPosition(movement.finalpos, board, Color.WHITE)) {
                        return FailureResult("King is in check")
                    }
                } else if (isPieceColorTargetingPosition(designatedPosition, board, Color.WHITE)) {
                    return FailureResult("King is in check")
                }
                return SuccessfulResult("Valid movement")
            }
            null -> return SuccessfulResult("Valid movement")
        }
    }
    private fun isPieceColorTargetingPosition(target : Position , board : Board , color : Color) : Boolean{
        val pieceFilterList = board.getPositionMap().values.filter { it.pieceColor == color && it.id != "KW" && it.id != "KB" }
        for (piece in pieceFilterList){
            val initPos = board.getPositionByPiece(piece)
            if(piece.movement[0].validateMovement(board , Movement(initPos , target)) is SuccessfulResult){
                return true
            }
        }
        return false
    }
}