package mychess.movement

import mychess.board.Board
import mychess.movement.Movement

interface MovementValidator {
    fun validateMovement(board: Board, movement: Movement): Boolean
}