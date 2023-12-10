package edu.austral.dissis.chess.common.movementvalidators.behaviour

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.rules.Game

interface MovementBehaviour {
    fun move(game: Game, movement: Movement): Game;
}