package edu.austral.dissis.chess.common.rules

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.movementvalidators.Movement

interface MovementRules {
    fun applyMove(game : Game, movement: Movement): StateEvaluatorResult
    fun isMovementSuccessful(gameState: GameState, movement: Movement): Boolean
}