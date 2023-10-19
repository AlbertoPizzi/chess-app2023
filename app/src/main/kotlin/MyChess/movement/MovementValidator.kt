package movement

import board.Board
import board.Position

interface MovementValidator {
    fun validateMovement( board: Board , movement: Movement): Boolean
}