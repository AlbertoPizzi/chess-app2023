package mychess.movement.concretemovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator

class VerticalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return (board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)
    }
}