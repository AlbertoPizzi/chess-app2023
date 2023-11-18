package mychess.movement.composedmovement

import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult
import edu.austral.dissis.chess.gui.Move
import mychess.board.Board

class SithMasterMV : MovementValidator {
    private val queenMV : MovementValidator = QueenMV()
    private val knightMV : MovementValidator = KnightMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(queenMV.validateMovement(board , movement ) is SuccessfulResult
            || knightMV.validateMovement(board , movement) is SuccessfulResult){
            return SuccessfulResult("This move goes with the Dark Side!")
        }
        return FailureResult("This move sides with the JEDI!")
    }

}