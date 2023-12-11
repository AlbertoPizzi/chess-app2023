package edu.austral.dissis.chess.common.rules

import edu.austral.dissis.chess.common.promotion.PromotionStrategy
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.history.HistoryUpdater
import edu.austral.dissis.chess.common.initializer.GameInitializer
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.PieceMover


data class Game(
    private val state: GameState,
    private val movementRules: MovementRules,
    private val promotionStrategy: PromotionStrategy
) {
    private val historyUpdater: HistoryUpdater = HistoryUpdater()
    private val pieceMover: PieceMover = PieceMover()

    constructor(gameInitializer: GameInitializer, movementRules: MovementRules, promotionStrategy: PromotionStrategy):  this(gameInitializer.init(), movementRules, promotionStrategy)



     fun applyMove(game : Game , move: Movement): StateEvaluatorResult {
        if (movementRules.isMovementSuccessful(game, move)) {
            val afterMoveGs = historyUpdater.updateHistory(pieceMover.moveTo(game, move))
            val newGameState = promotionStrategy.promote(afterMoveGs)
            val moveResult = movementRules.applyMove(newGameState, move)
            // newGameState.copy(turnManager = newGameState.turnManager.nextTurn())

            return moveResult
        }
        return InProgressStateResult(game)
    }

     fun getGameState(): GameState {
        return state
    }
    fun nextTurn(): Game{
        return this.copy(state = state.copy(turnManager = state.turnManager.nextTurn()))
    }

}