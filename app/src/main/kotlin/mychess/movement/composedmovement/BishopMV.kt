package mychess.movement.composedmovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.DiagonalMV
import mychess.movement.concretemovement.EatMV
import mychess.movement.concretemovement.PathIsFreeMV
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class BishopMV : MovementValidator {
    private val diagonalMV : MovementValidator = DiagonalMV ()
    private val freePath : MovementValidator = PathIsFreeMV()
    private val eatMV : MovementValidator = EatMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(board.isInBounds(movement.finalpos)){
            if(diagonalMV.validateMovement(board , movement) is SuccessfulResult
                && freePath.validateMovement(board , movement) is SuccessfulResult){
                return SuccessfulResult("yes")
            }
        }
        return FailureResult("nope")
    }
}