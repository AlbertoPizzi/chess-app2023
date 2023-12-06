package edu.austral.dissis.chess.common

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState

interface PromotionStrategy {
    fun promote(gameState: GameState ) : GameState

}