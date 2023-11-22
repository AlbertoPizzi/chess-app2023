package common.movementvalidators.concretemovement

import common.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class PositionIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        return if (board.getPositionMap().containsKey(movement.finalpos)) {
            FailureResult("Position isn't free")
        } else SuccessfulResult("Position is Free")
    }
}