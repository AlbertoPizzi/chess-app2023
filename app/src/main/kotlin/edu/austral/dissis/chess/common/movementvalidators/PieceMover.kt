package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.result.SuccessfulResult

class PieceMover {
    fun moveTo(gameState: GameState, movement: Movement): GameState { //aca puedo darle la posicion de una
        return gameState.getPositionMap().get(movement.initpos)!!.mb.move(gameState, movement)
    }
}