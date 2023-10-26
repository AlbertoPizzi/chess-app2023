package mychess.board

import mychess.piece.Piece

interface Board {
    fun getBoardType() : BoardType
    fun getColSize() : Int
    fun getRowSize() : Int
    fun getPositionMap() : Map<Position, Piece>
    fun isInBounds(position: Position) : Boolean
    fun getPositionByPiece(piece: Piece) : Position
    fun getPositions() : List<Position>
    fun getPieceByPosition(position: Position) : Piece?
}