package mychess.factory

import mychess.movement.composedmovement.*
import mychess.piece.Color
import mychess.piece.Piece
import mychess.piece.PieceType

class PieceFactory {
    companion object{
        private var id : Int = 0

        fun buildKing(pieceColor: Color): Piece {
            return Piece( PieceType.KING,"king " + id++   , pieceColor , listOf(KingMV()))
        }
        fun buildRook(pieceColor: Color): Piece {
            return Piece(PieceType.ROOK, "rook " + id++  , pieceColor , listOf(RookMV()))
        }
        fun buildBishop(pieceColor: Color): Piece {
            return Piece(PieceType.BISHOP, "bishop " + id++  , pieceColor , listOf(BishopMV()))
        }
        fun buildPawn(pieceColor: Color): Piece {
            return Piece(PieceType.PAWN,"pawn " + id++  , pieceColor , listOf(PawnMV()))
        }
        fun buildKnight(pieceColor: Color): Piece {
            return Piece(PieceType.KNIGHT,"knight " + id++  , pieceColor , listOf(KnightMV()))
        }
        fun buildQueen(pieceColor: Color): Piece {
            return Piece(PieceType.QUEEN,"queen " + id++  , pieceColor , listOf(QueenMV()))
        }
        fun buildSithMaster(pieceColor: Color): Piece{
            return Piece(PieceType.KING , "SM" + id++ , pieceColor , listOf(SithMasterMV()))
        }
    }
}