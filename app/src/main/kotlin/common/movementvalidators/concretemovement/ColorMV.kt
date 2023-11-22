package common.movementvalidators.concretemovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

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