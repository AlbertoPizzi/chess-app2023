package mychess.movement

import mychess.factory.BoardFactory
import mychess.board.Board
import mychess.board.Position
import mychess.piece.Piece
import mychess.result.SuccessfulResult

class PieceMover {
    fun moveTo(pieceToMove : Piece, finalpos : Position, board: Board): Board {
        val positionMapCopy : MutableMap<Position, Piece> = board.getPositionMap().toMutableMap()
        val initPos : Position = board.getPositionByPiece(pieceToMove)
        val movementList : List<MovementValidator> = pieceToMove.getMovementList()

        movementList.forEach{
            movementValidator: MovementValidator -> if(movementValidator.validateMovement(board , Movement(initPos , finalpos)) is SuccessfulResult){
                val target : Piece? = board.getPositionMap()[finalpos]
            if(target == null){
                positionMapCopy.remove(initPos)
            }else{
                positionMapCopy.remove(finalpos)
            }
            positionMapCopy[finalpos] = pieceToMove
            positionMapCopy.toMap()
            return BoardFactory.updateBoard(positionMapCopy , board)
            }
        }
        return board
    }
}