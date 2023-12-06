package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class JumpMV(private val x: Int, private val y: Int) : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        if (isJumping(movement)) {
            return SuccessfulResult("Movement Successful ")
        }
        return FailureResult("Piece is not moving correctly")
    }

    private fun isJumping(movement: Movement): Boolean {
        val auxX = abs(movement.finalpos.column - movement.initpos.column)
        val auxY = abs(movement.finalpos.row - movement.initpos.row)
        return (auxX == x && auxY == y) || (auxX == y && auxY == x)
    }
}