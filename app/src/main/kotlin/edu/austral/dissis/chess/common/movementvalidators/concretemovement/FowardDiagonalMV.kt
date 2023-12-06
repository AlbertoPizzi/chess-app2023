package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class FowardDiagonalMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
        when (board.getPieceByPosition(movement.initpos)!!.pieceColor) {
            Color.WHITE -> {
                if (movement.finalpos.row > movement.initpos.row) {
                    val auxX = (movement.finalpos.column - movement.initpos.column)
                    val auxY = (movement.finalpos.row - movement.initpos.row)
                    if (abs(auxX) == abs(auxY)) {
                        return SuccessfulResult("Piece moved correctly")
                    }
                    return FailureResult("Piece is not moving correctly")
                }
                return FailureResult("Piece is not moving correctly")
            }

            Color.BLACK -> {
                if (movement.finalpos.row < movement.initpos.row) {
                    val auxX = (movement.finalpos.column - movement.initpos.column)
                    val auxY = (movement.finalpos.row - movement.initpos.row)
                    if (abs(auxX) == abs(auxY)) {
                        return SuccessfulResult("Piece moved correctly")
                    }
                    return FailureResult("Piece is not moving correctly")
                }
                return FailureResult("Piece is not moving correctly")
            }
        }
    }
}