package edu.austral.dissis.chess.common.piece

import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.behaviour.MovementBehaviour

data class Piece(
    val type: PieceType,
    val id: String,
    val pieceColor: Color,
    val mv: List<MovementValidator>,
    val mb: MovementBehaviour
)