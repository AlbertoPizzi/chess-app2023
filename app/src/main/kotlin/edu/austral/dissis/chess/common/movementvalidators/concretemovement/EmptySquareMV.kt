package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class EmptySquareMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        if (board.getPositionMap().containsKey(movement.initpos)) {
            return SuccessfulResult("There's a piece in the initial position")
        } else return FailureResult("There's no piece there")
    }
}