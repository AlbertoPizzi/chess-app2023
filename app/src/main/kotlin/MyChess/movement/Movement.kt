package movement

import board.Board
import board.Position
import piece.Piece

data class Movement(
    val initpos: Position,
    val finalpos: Position
)
