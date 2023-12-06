package edu.austral.dissis.chess.common.piece

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement

interface MovementBehaviour {
    fun move(gameState: GameState, movement: Movement): GameState;
}