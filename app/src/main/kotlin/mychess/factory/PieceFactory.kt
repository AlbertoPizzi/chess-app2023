package mychess.factory

import mychess.movement.composedmovement.*
import mychess.piece.Color
import mychess.piece.Piece

class PieceFactory {
    companion object{
        private var id : Int = 0

        fun buildKing(pieceColor: Color): Piece {
            return Piece("king" + id++ , pieceColor , listOf(KingMV()))
        }
        fun buildRook(pieceColor: Color): Piece {
            return Piece("rook" + id++ , pieceColor , listOf(RookMV()))
        }
        fun buildBishop(pieceColor: Color): Piece {
            return Piece("bishop" + id++ , pieceColor , listOf(BishopMV()))
        }
        fun buildPawn(pieceColor: Color): Piece {
            return Piece("pawn" + id++ , pieceColor , listOf(PawnMV()))
        }
        fun buildKnight(pieceColor: Color): Piece {
            return Piece("knight" + id++ , pieceColor , listOf(KnightMV()))
        }
        fun buildQueen(pieceColor: Color): Piece {
            return Piece("queen" + id++ , pieceColor , listOf(QueenMV()))
        }
    }
}