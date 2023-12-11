package edu.austral.dissis.chess.common.gamestates

import edu.austral.dissis.chess.common.rules.Game

interface StateEvaluator {
    fun validate(game: Game): StateEvaluatorResult
}