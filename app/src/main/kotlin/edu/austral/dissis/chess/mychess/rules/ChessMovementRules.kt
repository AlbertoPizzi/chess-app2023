package edu.austral.dissis.chess.mychess.rules

import edu.austral.dissis.chess.common.rules.MovementRules
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluator
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game
import edu.austral.dissis.chess.mychess.movement.ChessMV
import edu.austral.dissis.chess.mychess.state.ChessStateEvaluator

class ChessMovementRules : MovementRules {
    private val gameValidator: MovementValidator = ChessMV()
    private val chessStateEvaluator: StateEvaluator = ChessStateEvaluator()

    override fun applyMove(game: Game, movement: Movement): StateEvaluatorResult {
        val gameState = game.getGameState()
        return when (val res = stateEvaluatorResult(gameState)) {
            is InProgressStateResult -> {
                InProgressStateResult(res.game)
            }

            is WinStateResult -> WinStateResult((stateEvaluatorResult(gameState) as WinStateResult).winner)
        }
    }

    override fun isMovementSuccessful(gameState: GameState, movement: Movement): Boolean {
        return gameValidator.validateMovement(gameState, movement) is SuccessfulResult
    }

    private fun stateEvaluatorResult(gs: GameState): StateEvaluatorResult {
        return chessStateEvaluator.validate(gs)
    }

}