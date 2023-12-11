package edu.austral.dissis.chess.mychess.rules

import edu.austral.dissis.chess.common.rules.MovementRules
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.*
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

        return when (val res = stateEvaluatorResult(game)) {
            is InProgressStateResult -> {
                InProgressStateResult(game)
            }
            is WinStateResult -> WinStateResult((stateEvaluatorResult(game) as WinStateResult).winner)
            is InvalidMoveStateResult -> InvalidMoveStateResult(res.message)
        }
    }

    override fun isMovementSuccessful(game: Game, movement: Movement): Boolean {
        return gameValidator.validateMovement(game, movement) is SuccessfulResult
    }

    private fun stateEvaluatorResult(gs: Game): StateEvaluatorResult {
        return chessStateEvaluator.validate(gs)
    }

}