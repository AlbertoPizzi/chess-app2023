package mychess.movement.concretemovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class EatMV : MovementValidator{
    private val colorCheck : MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(colorCheck.validateMovement(board, movement) is FailureResult){
            return SuccessfulResult("This piece can eat!")
        }
        else return FailureResult("Cannot eat your teammate!")
    }
}