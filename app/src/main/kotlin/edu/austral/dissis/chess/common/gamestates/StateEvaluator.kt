package edu.austral.dissis.chess.common.gamestates

import edu.austral.dissis.chess.common.game.GameState

interface StateEvaluator {
    fun validate(gameState: GameState): StateEvaluatorResult
}