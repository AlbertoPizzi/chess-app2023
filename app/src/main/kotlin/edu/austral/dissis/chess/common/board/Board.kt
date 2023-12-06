package edu.austral.dissis.chess.common.board

import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.gui.MoveResult

interface Board {
    fun getBoardType(): BoardType
    fun getColSize(): Int
    fun getRowSize(): Int
    fun getPositionMap(): Map<Position, Piece>
    fun isInBounds(position: Position): Boolean
    fun getPositionByPiece(piece: Piece): Position
    fun getPositions(): List<Position>
    fun getPieceByPosition(position: Position): Piece?
    fun getPieces(): List<Piece>
    fun getPositionByID(id: String): Position?
}