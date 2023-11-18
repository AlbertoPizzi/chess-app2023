package mychess.movement.composedmovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

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