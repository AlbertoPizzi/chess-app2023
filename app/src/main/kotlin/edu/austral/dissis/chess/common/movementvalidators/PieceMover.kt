package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.result.SuccessfulResult

class PieceMover {
    fun moveTo(pieceToMove: Piece, finalpos: Position, gameState: GameState): Board { //aca puedo darle la posicion de una
        val board = gameState.getBoardHistory().last()
        val positionMapCopy: MutableMap<Position, Piece> = board.getPositionMap().toMutableMap()
        val initPos: Position = board.getPositionByPiece(pieceToMove)
        val movementList: List<MovementValidator> = pieceToMove.movement
        movementList.forEach { movementValidator: MovementValidator ->
            if (movementValidator.validateMovement(gameState, Movement(initPos, finalpos)) is SuccessfulResult) {
                val target: Piece? = board.getPositionMap()[finalpos]
                if (target == null) {
                    positionMapCopy.remove(initPos)
                } else {
                    positionMapCopy.remove(finalpos)
                    positionMapCopy.remove(initPos)
                }
                positionMapCopy[Position(finalpos.column, finalpos.row)] = pieceToMove
                positionMapCopy.toMap()
                return BoardFactory.updateBoard(positionMapCopy, board)
            }
        }
        return board
    }
}