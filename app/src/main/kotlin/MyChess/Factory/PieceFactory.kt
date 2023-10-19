package Factory

import movement.composedmovement.*
import piece.Piece

class PieceFactory {
    companion object{
        private var id : Int = 0

        fun buildKing(pieceColor: piece.Color): Piece{
            return Piece("king" + id++ , pieceColor , listOf(KingMV()))
        }
        fun buildRook(pieceColor: piece.Color): Piece{
            return Piece("rook" + id++ , pieceColor , listOf(RookMV()))
        }
        fun buildBishop(pieceColor: piece.Color): Piece{
            return Piece("bishop" + id++ , pieceColor , listOf(BishopMV()))
        }
        fun buildPawn(pieceColor: piece.Color): Piece{
            return Piece("pawn" + id++ , pieceColor , listOf(PawnMV()))
        }
        fun buildKnight(pieceColor: piece.Color): Piece{
            return Piece("knight" + id++ , pieceColor , listOf(KnightMV()))
        }
        fun buildQueen(pieceColor: piece.Color): Piece{
            return Piece("queen" + id++ , pieceColor , listOf(QueenMV()))
        }
    }
}