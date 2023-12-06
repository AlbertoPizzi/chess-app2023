package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.PieceType
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class PromotionMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
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