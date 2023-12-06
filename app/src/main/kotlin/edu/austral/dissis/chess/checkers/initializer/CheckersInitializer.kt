package edu.austral.dissis.chess.checkers.initializer

import edu.austral.dissis.chess.common.factory.GameStateFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.initializer.GameInitializer

class CheckersInitializer : GameInitializer {
    private val gameStateFactory = GameStateFactory()
    override fun init(): GameState {
        return gameStateFactory.checkersStateBuilder()
    }
}