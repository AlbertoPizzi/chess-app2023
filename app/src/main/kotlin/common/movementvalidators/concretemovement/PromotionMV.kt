package common.movementvalidators.concretemovement

import common.board.Board
import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.piece.PieceType
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class PromotionMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        if (isPromotionable(board, movement)) {
            return SuccessfulResult("Promotion!")
        }
        return FailureResult("Not promotionable!")
    }

    private fun isPromotionable(board: Board, movement: Movement): Boolean {
        val pieceToPromote = board.getPieceByPosition(movement.initpos)!!
        if (pieceToPromote.type == PieceType.PAWN
            || pieceToPromote.type == PieceType.CHECKER
        ) {
            return when (pieceToPromote.pieceColor) {
                Color.WHITE -> movement.finalpos.row == board.getRowSize()
                Color.BLACK -> movement.finalpos.row == 1
            }
        }
        return false
    }
}