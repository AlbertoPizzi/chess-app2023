package mychess.movement.composedmovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class QueenMV : MovementValidator {
    private val rookMV : MovementValidator = RookMV()
    private val bishopMV : MovementValidator = BishopMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(rookMV.validateMovement(board , movement) is SuccessfulResult){
            return SuccessfulResult("Rook Movement is valid")
        }
        else if(bishopMV.validateMovement(board, movement) is SuccessfulResult){
            return SuccessfulResult("Bishop Movement is valid")
        }
        else return FailureResult("Movement not valid")
    }
}