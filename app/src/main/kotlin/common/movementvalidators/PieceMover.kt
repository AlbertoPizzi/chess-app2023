package common.movementvalidators

import common.factory.BoardFactory
import common.board.Board
import common.board.Position
import common.game.GameState
import common.piece.Piece
import common.result.SuccessfulResult

class PieceMover {
    fun moveTo(pieceToMove: Piece, finalpos: Position, gameState: GameState): Board {
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