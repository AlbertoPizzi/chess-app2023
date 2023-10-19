package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.piece.Piece

class ColorMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val target : Piece? = board.getPieceByPosition(movement.finalpos)
        if(target != null){
            if(target.getPieceColor() == board.getPieceByPosition(movement.initpos).getPieceColor()){
                return true
            }
        }
        return false
    }
}