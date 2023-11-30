package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class FowardDiagonalMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
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