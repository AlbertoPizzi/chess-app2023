package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class VerticalMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        if ((board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)) {
            return SuccessfulResult("Its a vertical Movement!")
        }
        return FailureResult("It is not a Vertical Movement!")
    }
}