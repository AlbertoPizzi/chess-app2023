package edu.austral.dissis.chess.common

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.movementvalidators.Movement

interface MovementRules { //TODO Cambiar nombre a algo mas entendible
    fun makeAMove(movement: Movement, gameState: GameState): StateEvaluatorResult //TODO cambiar el nombre a applyMove
    fun isMovementSuccessful( gameState: GameState , move: Movement): Boolean
}