package common

import common.game.GameState
import common.movementvalidators.Movement

interface Promoter {
    fun promote(gameState: GameState , movement : Movement) : GameState
}