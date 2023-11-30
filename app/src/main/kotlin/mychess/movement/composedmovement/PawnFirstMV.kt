package mychess.movement.composedmovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class PawnFirstMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
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