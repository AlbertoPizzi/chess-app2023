package common.factory

import common.movementvalidators.concretemovement.*
import common.movementvalidators.logicalmovement.AndMV
import common.movementvalidators.logicalmovement.OrMV
import mychess.movement.composedmovement.*
import common.piece.Color
import common.piece.Piece
import common.piece.PieceType
import kotlin.io.path.Path


class PieceFactory {
    companion object {

        fun buildKing(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), LimitMV(1) , PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), LimitMV(1), PositionIsFreeMV()))
            val diagonalMV = AndMV(listOf(DiagonalMV(), LimitMV(1), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(EatMV(), LimitMV(1)))
            val movement = OrMV(listOf(horizontalMV, verticalMV, diagonalMV, eatMV))
            return Piece(PieceType.KING, id, pieceColor, listOf(movement))
        }

        fun buildRook(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val eatHorizontalMV = AndMV(listOf(HorizontalMV(), PathIsFreeMV(), EatMV()))
            val eatVerticalMV = AndMV(listOf(VerticalMV(), PathIsFreeMV(), EatMV()))
            val movement = OrMV(listOf(horizontalMV, verticalMV, eatHorizontalMV, eatVerticalMV))
            return Piece(PieceType.ROOK, id, pieceColor, listOf(movement))
        }

        fun buildBishop(id: String, pieceColor: Color): Piece {
            val normalmv = AndMV(listOf(DiagonalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(DiagonalMV(), EatMV(), PathIsFreeMV()))
            val movement = OrMV(listOf(normalmv, eatMV))
            return Piece(PieceType.BISHOP, id, pieceColor, listOf(movement))
        }

        fun buildPawn(id: String, pieceColor: Color): Piece {
            val normalmv = AndMV(
                listOf(
                    FowardMV(),
                    LimitMV(1),
                    PositionIsFreeMV()
                )
            )

            val firstmv = AndMV(
                listOf(
                    FowardMV(),
                    LimitMV(2), PositionIsFreeMV(),
                    PawnFirstMV(), PathIsFreeMV()
                )
            )

            val eat = AndMV(
                listOf(
                    FowardDiagonalMV(),
                    LimitMV(1),
                    EatMV()
                )
            )
            val pawnMV = listOf(
                OrMV(
                    listOf(
                        normalmv,
                        firstmv,
                        eat
                    )
                )
            )
            return Piece(PieceType.PAWN, id, pieceColor, pawnMV)
        }

        fun buildKnight(id: String, pieceColor: Color): Piece {
            val normalmv = AndMV(listOf(JumpMV(1, 2), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(JumpMV(1, 2), EatMV()))
            val movement = OrMV(listOf(normalmv, eatMV))
            return Piece(PieceType.KNIGHT, id, pieceColor, listOf(movement))
        }

        fun buildQueen(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val diagonalMV = AndMV(listOf(DiagonalMV(), PathIsFreeMV(), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(EatMV(), PathIsFreeMV()))
            val movement = OrMV(listOf(horizontalMV, verticalMV, diagonalMV, eatMV))

            return Piece(PieceType.QUEEN, id, pieceColor, listOf(movement))
        }

        fun buildSithMaster(id: String, pieceColor: Color): Piece {
            return Piece(PieceType.KING, id, pieceColor, listOf(SithMasterMV()))
        }

        fun buildChecker(id: String, pieceColor: Color): Piece {
            val fowardDiagonalMV = AndMV(listOf(FowardDiagonalMV(), LimitMV(1), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(FowardDiagonalMV(), LimitMV(2), EnemyInBetweenMV() , PositionIsFreeMV()))
            val movement = OrMV(listOf(fowardDiagonalMV, eatMV))
            return Piece(PieceType.PAWN, id, pieceColor, listOf(movement))
        }
    }
}