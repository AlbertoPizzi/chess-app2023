package common.gamestates

import common.game.GameState

interface StateEvaluator {
    fun validate(gameState: GameState): StateEvaluatorResult
}