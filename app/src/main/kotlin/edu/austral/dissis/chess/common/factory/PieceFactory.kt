package edu.austral.dissis.chess.common.factory

import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.piece.PieceType

interface PieceFactory {

    fun buildPiece(pieceType: PieceType):Piece
}