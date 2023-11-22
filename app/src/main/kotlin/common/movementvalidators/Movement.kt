package common.movementvalidators

import mychess.board.Position

data class Movement(
    val initpos: Position,
    val finalpos: Position
)
