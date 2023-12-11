package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class CheckersMV : MovementValidator {

    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val board = game.getGameState().board
        when (basicCheckers(game, movement)) {
            true -> {
                return board.getPositionMap()[movement.initpos]!!.mv[0].validateMovement(game, movement)
            }

            false -> {
                return FailureResult("There's no piece in the initial position")
            }
        }
    }

    private fun basicCheckers(gameState: Game, movement: Movement): Boolean {
        return (CheckersBasicMV().validateMovement(gameState, movement) is SuccessfulResult)
    }
}
