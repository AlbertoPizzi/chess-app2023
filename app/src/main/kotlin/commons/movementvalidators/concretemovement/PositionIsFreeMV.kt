package commons.movementvalidators.concretemovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class PositionIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        return if(board.getPositionMap().containsKey(movement.finalpos)){
            FailureResult("Position isn't free")
        } else SuccessfulResult("Position is Free")
    }
}