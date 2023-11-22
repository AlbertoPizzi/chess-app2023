package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.piece.Color
import mychess.piece.Piece
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class ColorMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val target : Piece? = board.getPieceByPosition(movement.finalpos)
        if(target != null){
            if(target.getPieceColor() == (board.getPieceByPosition(movement.initpos)?.getPieceColor()) ){//TODO: FIJATE EN ESTO BOLUDO
                return SuccessfulResult("Pieces are the same Color!")
            }
        }
        return FailureResult("Pieces are different color!")
    }
}