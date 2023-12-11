package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class EatMV : MovementValidator {
    private val colorCheck: MovementValidator = ColorMV()
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        if (board.getPositionMap().containsKey(movement.finalpos) && colorCheck.validateMovement(
                game,
                movement
            ) is FailureResult
        ) {
            return SuccessfulResult("This piece can eat!")
        } else return FailureResult("Cannot eat your teammate!")
    }
}