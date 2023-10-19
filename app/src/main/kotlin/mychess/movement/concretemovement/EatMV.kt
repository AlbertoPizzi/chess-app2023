package mychess.movement.concretemovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator

class EatMV : MovementValidator{
    private val colorCheck : MovementValidator = ColorMV()
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return !colorCheck.validateMovement(board, movement)
    }
}