package mychess.movement

import mychess.board.Board
import mychess.movement.Movement
import mychess.result.ResultValidator

interface MovementValidator {
    fun validateMovement(board: Board, movement: Movement): ResultValidator
}