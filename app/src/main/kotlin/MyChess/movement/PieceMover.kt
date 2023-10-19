package movement

import Factory.BoardFactory
import board.Board
import board.Position
import piece.Piece

class PieceMover {
    fun moveTo(pieceToMove : Piece, finalpos : Position , board: Board ): Board{
        val positionMapCopy : MutableMap<Position, Piece> = board.getPositionMap().toMutableMap()
        val initPos : Position = board.getPositionByPiece(pieceToMove)
        val movementList : List<MovementValidator> = pieceToMove.movementList

        movementList.forEach{
            movementValidator: MovementValidator -> if(movementValidator.validateMovement(board , Movement(initPos , finalpos))){
                val target : Piece? = board.getPositionMap()[finalpos]
            if(target == null){
                positionMapCopy.remove(initPos)
            }else{
                positionMapCopy.remove(finalpos)
            }
            positionMapCopy[finalpos] = pieceToMove
            positionMapCopy.toMap()
            return BoardFactory.createNewBoard(positionMapCopy , board)
            }
        }
        return board
    }
}