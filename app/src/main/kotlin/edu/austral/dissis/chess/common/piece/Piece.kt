package edu.austral.dissis.chess.common.piece

import edu.austral.dissis.chess.common.movementvalidators.MovementValidator

data class Piece(
    val type: PieceType,
    val id: String,
    val pieceColor: Color,
    val movement: List<MovementValidator>
)