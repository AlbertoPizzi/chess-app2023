package mychess.movement

import mychess.board.Position

data class Movement(
    val initpos: Position,
    val finalpos: Position
)
