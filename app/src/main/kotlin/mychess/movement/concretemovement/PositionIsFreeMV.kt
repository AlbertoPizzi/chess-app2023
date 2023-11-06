package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class PositionIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        return if(board.getPositionMap().containsKey(movement.finalpos)){
            FailureResult("Position isn't free")
        } else SuccessfulResult("Position is Free")
    }
}