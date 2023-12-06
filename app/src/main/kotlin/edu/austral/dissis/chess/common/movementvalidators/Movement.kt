package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.board.Position

data class Movement(
    val initpos: Position,
    val finalpos: Position
)
