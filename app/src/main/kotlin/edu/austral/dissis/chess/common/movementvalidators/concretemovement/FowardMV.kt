package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class FowardMV : MovementValidator {
    private val verticalMV: MovementValidator = VerticalMV()
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        when (board.getPieceByPosition(movement.initpos)!!.pieceColor) {
            Color.WHITE -> {
                if (verticalMV.validateMovement(game, movement) is SuccessfulResult
                    && movement.finalpos.row > movement.initpos.row
                ) {
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("invalid movment")
            }

            Color.BLACK -> {
                if (verticalMV.validateMovement(game, movement) is SuccessfulResult
                    && movement.finalpos.row < movement.initpos.row
                ) {
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("Invalid movement")
            }
        }
    }
}