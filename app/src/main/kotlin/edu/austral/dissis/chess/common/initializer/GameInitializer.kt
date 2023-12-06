package edu.austral.dissis.chess.common.initializer

import edu.austral.dissis.chess.common.game.GameState

interface GameInitializer {
    fun init(): GameState
}