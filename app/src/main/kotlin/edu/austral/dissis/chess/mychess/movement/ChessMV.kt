package edu.austral.dissis.chess.mychess.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.CurrentPlayerMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game
import edu.austral.dissis.chess.mychess.movement.composedmovement.NotInCheckMV

class ChessMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        when (basicValidators(game, movement)) {
            true -> {
                return board.getPositionMap()[movement.initpos]!!.mv[0].validateMovement(game, movement)
            }

            false -> {
                return FailureResult("Invalid Movement")
            }
        }
    }

    private fun basicValidators(game: Game, movement: Movement): Boolean {
        return (EmptySquareMV().validateMovement(game, movement) is SuccessfulResult
                && NotInCheckMV().validateMovement(game, movement) is SuccessfulResult
                && CurrentPlayerMV().validateMovement(game, movement) is SuccessfulResult)
    }
}
