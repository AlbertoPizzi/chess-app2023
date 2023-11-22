package common.movementvalidators

import mychess.board.Board
import common.result.ResultValidator

interface MovementValidator {
    fun validateMovement(board: Board, movement: Movement): ResultValidator
}