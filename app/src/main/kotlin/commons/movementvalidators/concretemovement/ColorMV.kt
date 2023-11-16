package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.piece.Piece
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

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