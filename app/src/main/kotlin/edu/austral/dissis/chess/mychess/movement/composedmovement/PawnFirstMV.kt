package edu.austral.dissis.chess.mychess.movement.composedmovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class PawnFirstMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
        //White
        if (board.getPieceByPosition(movement.initpos)?.pieceColor == Color.WHITE
            && movement.initpos.row == 2
        ) {
            return SuccessfulResult("White pawn hasn't moved")
        }
        //black
        if (board.getPieceByPosition(movement.initpos)?.pieceColor == Color.BLACK
            && movement.initpos.row == 7
        ) {
            return SuccessfulResult("Black pawn hasn't moved")
        }
        return FailureResult("Pawn has already moved")
    }

}