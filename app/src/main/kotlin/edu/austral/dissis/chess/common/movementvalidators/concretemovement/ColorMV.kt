package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class ColorMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
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