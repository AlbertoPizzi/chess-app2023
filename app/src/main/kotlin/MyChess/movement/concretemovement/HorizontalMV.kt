package movement.concretemovement

import board.Board
import board.Position
import movement.Movement
import movement.MovementValidator

class HorizontalMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return (board.isInBounds(movement.finalpos) && (movement.initpos.row == movement.finalpos.row))
    }
}