package movement.concretemovement

import board.Board
import movement.Movement
import movement.MovementValidator

class VerticalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return (board.isInBounds(movement.finalpos)) && (movement.initpos.column == movement.finalpos.column)
    }
}