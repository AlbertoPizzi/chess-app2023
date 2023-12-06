package edu.austral.dissis.chess.common.game

import edu.austral.dissis.chess.checkers.CheckersMovementRules
import edu.austral.dissis.chess.checkers.initializer.CheckersInitializer
import edu.austral.dissis.chess.checkers.promotion.CheckersPromotionStrategy
import edu.austral.dissis.chess.common.Adapter
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.common.rules.Rules
import edu.austral.dissis.chess.common.rules.RulesImpl
import edu.austral.dissis.chess.mychess.ChessPromotionStrategy
import edu.austral.dissis.chess.mychess.ChessMovementRules
import edu.austral.dissis.chess.mychess.initializer.ChessInitializer

class ApeEngine(rules: Rules) : GameEngine {
    private val game = rules

    companion object {
        fun chessEngine(): ApeEngine {
            return ApeEngine(RulesImpl(ChessInitializer() , ChessMovementRules() , ChessPromotionStrategy() ))
        }

        fun checkersEngine(): ApeEngine {
            return ApeEngine(RulesImpl(CheckersInitializer() , CheckersMovementRules() , CheckersPromotionStrategy() ))
        }

    }

    override fun init(): InitialState {
        return game.getAdapter().adaptGameStateToInitialState(game.init())
    }

    override fun applyMove(move: Move): MoveResult {
        return game.applyMove(move)
    }


}