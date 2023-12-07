package edu.austral.dissis.chess.mychess.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.CurrentPlayerMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.mychess.movement.composedmovement.NotInCheckMV

class ChessMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
        when (basicValidators(gameState, movement)) {
            true -> {
                return board.getPositionMap()[movement.initpos]!!.mv[0].validateMovement(gameState, movement)
            }

            false -> {
                return FailureResult("Invalid Movement")
            }
        }
    }

    private fun basicValidators(gameState: GameState, movement: Movement): Boolean {
        return (EmptySquareMV().validateMovement(gameState, movement) is SuccessfulResult
                && NotInCheckMV().validateMovement(gameState, movement) is SuccessfulResult
                && CurrentPlayerMV().validateMovement(gameState, movement) is SuccessfulResult)
    }
}
