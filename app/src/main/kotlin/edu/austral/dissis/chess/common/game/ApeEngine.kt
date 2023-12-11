package edu.austral.dissis.chess.common.game

import edu.austral.dissis.chess.checkers.rules.CheckersMovementRules
import edu.austral.dissis.chess.checkers.initializer.CheckersInitializer
import edu.austral.dissis.chess.checkers.promotion.CheckersPromotionStrategy
import edu.austral.dissis.chess.common.Adapter
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.InvalidMoveStateResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.common.rules.Game
import edu.austral.dissis.chess.mychess.promotion.ChessPromotionStrategy
import edu.austral.dissis.chess.mychess.rules.ChessMovementRules
import edu.austral.dissis.chess.mychess.initializer.CapaBlancaInitializer
import edu.austral.dissis.chess.mychess.initializer.ChessInitializer
import edu.austral.dissis.chess.mychess.initializer.JediKnightsInitializer

class ApeEngine(val game: Game) : GameEngine {
    private var currentGame = game
    private val adapter = Adapter()

    companion object {
        fun chessEngine(): ApeEngine {
            return ApeEngine(Game(ChessInitializer(), ChessMovementRules(), ChessPromotionStrategy()))
        }

        fun checkersEngine(): ApeEngine {
            return ApeEngine(Game(CheckersInitializer(), CheckersMovementRules(), CheckersPromotionStrategy()))
        }

        fun jediKnightsEngine(): ApeEngine {
            return ApeEngine(Game(JediKnightsInitializer(), ChessMovementRules(), ChessPromotionStrategy()))
        }

        fun capaBlancaEngine(): ApeEngine {
            return ApeEngine(Game(CapaBlancaInitializer(), ChessMovementRules(), ChessPromotionStrategy()))
        }

    }

    override fun init(): InitialState {
        return adapter.adaptGameStateToInitialState(currentGame.getGameState())
    }

    override fun applyMove(move: Move): MoveResult {
        val movement: Movement = adapter.translateMovement(move)
        return when (val stateResult = currentGame.applyMove(currentGame ,movement)) {
            is InProgressStateResult -> {
                this.currentGame = stateResult.game
                println(currentGame.getGameState().getCurrentPlayer().toString())
                adapter.adaptGameState(currentGame)
            }

            is WinStateResult -> GameOver(adapter.colorAdapter(stateResult.winner))
            is InvalidMoveStateResult ->
                InvalidMove(stateResult.message)
        }
    }

    fun getAdapter() : Adapter{
        return adapter
    }


}