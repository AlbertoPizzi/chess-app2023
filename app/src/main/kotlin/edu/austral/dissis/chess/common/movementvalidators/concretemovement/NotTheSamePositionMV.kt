package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class NotTheSamePositionMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        if (movement.initpos == movement.finalpos) {
            return FailureResult("From and to are the same")
        }
        return SuccessfulResult("Not the same position")
    }
}