package edu.austral.dissis.chess.common.movementvalidators.behaviour

import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.factory.GameStateFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.rules.Game

class NormalMovementBehaviour() : MovementBehaviour {
    override fun move(game: Game, movement: Movement): Game {
        return game.copy(state = GameStateFactory().chessGameStateBuilder().copy(board = BoardFactory.createBoardFromReference(game.getGameState().board, movement)) )
    }
}