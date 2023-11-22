package common.movementvalidators

import common.board.Board
import common.result.ResultValidator

interface MovementValidator {
    fun validateMovement(board: Board, movement: Movement): ResultValidator
}