package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class CheckersMV : MovementValidator {

    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
        when (basicCheckers(gameState, movement)) {
            true -> {
                return board.getPositionMap()[movement.initpos]!!.mv[0].validateMovement(gameState, movement)
            }
            false -> {
                return FailureResult("There's no piece in the initial position")
            }
        }
    }
    private fun basicCheckers(gameState: GameState, movement: Movement): Boolean {
        return (EmptySquareMV().validateMovement(gameState, movement) is SuccessfulResult)
    }
}
