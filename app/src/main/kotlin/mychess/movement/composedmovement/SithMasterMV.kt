package mychess.movement.composedmovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class SithMasterMV : MovementValidator {
    private val queenMV: MovementValidator = QueenMV()
    private val knightMV: MovementValidator = KnightMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if (queenMV.validateMovement(board, movement) is SuccessfulResult
            || knightMV.validateMovement(board, movement) is SuccessfulResult
        ) {
            return SuccessfulResult("This move goes with the Dark Side!")
        }
        return FailureResult("This move sides with the JEDI!")
    }

}