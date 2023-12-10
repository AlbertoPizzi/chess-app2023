package edu.austral.dissis.chess.common.promotion

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.rules.Game

interface PromotionStrategy {
    fun promote(game : Game): Game

}