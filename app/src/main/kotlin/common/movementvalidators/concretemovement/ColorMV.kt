package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class ColorMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        val target: Piece? = board.getPieceByPosition(movement.finalpos)
        if (target != null) {
            if (target.pieceColor == (board.getPieceByPosition(movement.initpos)?.pieceColor)
            ) {
                return SuccessfulResult("Pieces are the same Color!")
            }
        }
        return FailureResult("Pieces are different color!")
    }
}