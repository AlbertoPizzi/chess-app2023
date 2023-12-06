package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.MovementBehaviour

class NormalMovementBehaviour () : MovementBehaviour {
    override fun move(gameState: GameState, movement: Movement): GameState {
        return gameState.copy(board = BoardFactory.createBoardFromReference(gameState.board, movement))
    }
}