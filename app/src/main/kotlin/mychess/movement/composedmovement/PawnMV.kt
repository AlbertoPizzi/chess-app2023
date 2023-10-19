package mychess.movement.composedmovement

import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.ColorMV
import mychess.movement.concretemovement.DiagonalMV
import mychess.movement.concretemovement.PathIsFreeMV
import mychess.movement.concretemovement.VerticalMV
import mychess.piece.Piece
import mychess.piece.PieceType

class PawnMV : MovementValidator {
    private val verticalMV : MovementValidator = VerticalMV() //moves
    private val diagonalMV : MovementValidator = DiagonalMV() //eats
    private val colorCheck : MovementValidator = ColorMV() //checks Color
    private val pathIsFreeMV : MovementValidator = PathIsFreeMV() //checks free path

    override fun validateMovement(board: Board, movement: Movement): Boolean {
        TODO("implement this method and all the other Validator")
    }
}