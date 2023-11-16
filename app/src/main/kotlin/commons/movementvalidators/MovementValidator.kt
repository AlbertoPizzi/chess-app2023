package commons.movementvalidators

import mychess.board.Board
import commons.result.ResultValidator

interface MovementValidator {
    fun validateMovement(board: Board, movement: Movement): ResultValidator
}