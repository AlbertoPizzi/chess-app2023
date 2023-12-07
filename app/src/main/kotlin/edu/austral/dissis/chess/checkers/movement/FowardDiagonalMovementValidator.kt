package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class FowardDiagonalMovementValidator() : MovementValidator {

    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        when (gameState.getCurrentPlayer()) {
            edu.austral.dissis.chess.common.piece.Color.WHITE -> {
                if (movement.finalpos.row > movement.initpos.row) {
                    val auxX = (movement.finalpos.column - movement.initpos.column)
                    val auxY = (movement.finalpos.row - movement.initpos.row)
                    if (abs(auxX) == abs(auxY)) {
                        return SuccessfulResult("")
                    }
                    return FailureResult("Piece is not moving correctly")
                }
                return FailureResult("Piece is not moving correctly")
            }

            edu.austral.dissis.chess.common.piece.Color.BLACK -> {
                if (movement.finalpos.row < movement.initpos.row) {
                    val auxX = (movement.finalpos.column - movement.initpos.column)
                    val auxY = (movement.finalpos.row - movement.initpos.row)
                    if (abs(auxX) == abs(auxY)) {
                        return SuccessfulResult("")
                    }
                    return FailureResult("Piece is not moving correctly")
                }
                return FailureResult("Piece is not moving correctly")
            }
        }
    }
}