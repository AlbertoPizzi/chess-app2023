package common.factory

import common.movementvalidators.concretemovement.DiagonalMV
import mychess.movement.composedmovement.*
import common.piece.Color
import common.piece.Piece
import common.piece.PieceType

class PieceFactory {
    companion object {

        fun buildKing(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.KING, id, pieceColor, listOf(KingMV()))
        }

        fun buildRook(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.ROOK, id, pieceColor, listOf(RookMV()))
        }

        fun buildBishop(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.BISHOP, id, pieceColor, listOf(BishopMV()))
        }

        fun buildPawn(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.PAWN, id, pieceColor, listOf(PawnMV()))
        }

        fun buildKnight(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.KNIGHT, id, pieceColor, listOf(KnightMV()))
        }

        fun buildQueen(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.QUEEN, id, pieceColor, listOf(QueenMV()))
        }

        fun buildSithMaster(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.KING, id, pieceColor, listOf(SithMasterMV()))
        }
        fun buildChecker(id: String , pieceColor: Color) : Piece{
            return Piece(PieceType.PAWN , id , pieceColor , listOf(DiagonalMV()))
        }
    }
}