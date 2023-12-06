package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.ResultValidator

class MaxMV(private val max: Int, private val pieceID: String) : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        return TODO("you gotta do this")
    }

}