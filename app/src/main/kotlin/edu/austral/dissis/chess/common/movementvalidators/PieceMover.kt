package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.rules.Game

class PieceMover {
    fun moveTo(game: Game, movement: Movement): Game {
        val gameState = game.getGameState().getPositionMap().get(movement.initpos)!!.mb.move(game, movement)
        return game.copy(state = gameState.getGameState())
    }
}