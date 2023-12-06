package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class HorizontalMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
//        val board = gameState.getBoardHistory().last()
        return if ((movement.initpos.row == movement.finalpos.row)) {
            SuccessfulResult("It is a horizontal Movement")
        } else FailureResult("It is not a horizontal movement!")
    }
}