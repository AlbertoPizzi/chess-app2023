package common.piece

import common.movementvalidators.MovementValidator

data class Piece(
    val type: PieceType,
    val id: String,
    val pieceColor: Color,
    val movement: List<MovementValidator>
)