package mychess.movement.composedmovement

import com.sun.net.httpserver.Authenticator.Success
import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.ColorMV
import mychess.movement.concretemovement.DiagonalMV
import mychess.movement.concretemovement.EatMV
import mychess.movement.concretemovement.PathIsFreeMV
import mychess.piece.Piece
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class BishopMV : MovementValidator {
    private val diagonalMV : MovementValidator = DiagonalMV ()
    private val freePath : MovementValidator = PathIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    private val colorMV : MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val target : Piece? = board.getPositionMap().get(movement.finalpos)
            if(diagonalMV.validateMovement(board , movement) is SuccessfulResult
                && freePath.validateMovement(board , movement) is SuccessfulResult
            ){
                return SuccessfulResult("Valid movement")
            }
        if(target != null && colorMV.validateMovement(board, movement) is SuccessfulResult){
            return FailureResult("Not valid movement")
        }
        else if (target != null && colorMV.validateMovement(board, movement) is FailureResult){
            return SuccessfulResult("valid movement")
        }
        return FailureResult("Invalid movement")
    }
}