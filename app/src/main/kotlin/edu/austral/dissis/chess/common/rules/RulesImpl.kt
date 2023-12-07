package edu.austral.dissis.chess.common.rules

import edu.austral.dissis.chess.common.PromotionStrategy
import edu.austral.dissis.chess.common.MovementRules
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.history.HistoryUpdater
import edu.austral.dissis.chess.common.initializer.GameInitializer
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.PieceMover


class RulesImpl(
    private val gameInitializer: GameInitializer,
    private val movementRules: MovementRules,
    private val promotionStrategy: PromotionStrategy
) : Rules {
    private var gameState: GameState = init()
    private val historyUpdater: HistoryUpdater = HistoryUpdater()
    private val pieceMover: PieceMover = PieceMover()

    override fun init(): GameState {
        return gameInitializer.init()
    }

    override fun applyMove(move: Movement): StateEvaluatorResult {
        if (movementRules.isMovementSuccessful(gameState, move)) {
            val afterMoveGs = historyUpdater.updateHistory(pieceMover.moveTo(gameState, move))
            val newGameState = promotionStrategy.promote(afterMoveGs)
            val moveResult = movementRules.applyMove(newGameState, move)
            gameState = newGameState.copy(turnManager = newGameState.turnManager.nextTurn())
            return moveResult
        }
        return InProgressStateResult()
    }

    override fun getGameState(): GameState {
        return gameState
    }

}