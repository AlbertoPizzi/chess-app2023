package edu.austral.dissis.chess.common.factory

import edu.austral.dissis.chess.checkers.movement.CheckerMB
import edu.austral.dissis.chess.checkers.movement.CrownedMb
import edu.austral.dissis.chess.checkers.movement.NotObligatedToEatValidator
import edu.austral.dissis.chess.common.movementvalidators.behaviour.NormalMovementBehaviour
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.*
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.OrMV
import edu.austral.dissis.chess.mychess.movement.composedmovement.*
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.piece.PieceType


class PieceFactory {

    companion object {

        fun buildKing(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), LimitMV(1), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), LimitMV(1), PositionIsFreeMV()))
            val diagonalMV = AndMV(listOf(DiagonalMV(), LimitMV(1), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(EatMV(), LimitMV(1)))
            val movement = OrMV(listOf(horizontalMV, verticalMV, diagonalMV, eatMV))
            return Piece(PieceType.KING, id, pieceColor, listOf(movement), NormalMovementBehaviour())
        }

        fun buildRook(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val eatHorizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatVerticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val movement = OrMV(listOf(horizontalMV, verticalMV, eatHorizontalMV, eatVerticalMV))
            return Piece(PieceType.ROOK, id, pieceColor, listOf(movement), NormalMovementBehaviour())
        }

        fun buildBishop(id: String, pieceColor: Color): Piece {
            val normalmv = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(DiagonalMV(), EatMV(), DiagonalPathIsFreeMV()))
            val movement = OrMV(listOf(normalmv, eatMV))
            return Piece(PieceType.BISHOP, id, pieceColor, listOf(movement), NormalMovementBehaviour())
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
                    PawnFirstMV(), HorizontalOrVerticalPathsAreFreeMV()
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
            return Piece(PieceType.PAWN, id, pieceColor, pawnMV, NormalMovementBehaviour())
        }

        fun buildKnight(id: String, pieceColor: Color): Piece {
            val normalmv = AndMV(listOf(JumpMV(1, 2), PositionIsFreeMV()))
            val eatMV = AndMV(listOf(JumpMV(1, 2), EatMV()))
            val movement = OrMV(listOf(normalmv, eatMV))
            return Piece(PieceType.KNIGHT, id, pieceColor, listOf(movement), NormalMovementBehaviour())
        }

        fun buildQueen(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val diagonalMV = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), PositionIsFreeMV()))
            val eatHorizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatVerticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatDiagonalMV = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), EatMV()))
            val movement =
                OrMV(listOf(horizontalMV, verticalMV, diagonalMV, eatHorizontalMV, eatVerticalMV, eatDiagonalMV))

            return Piece(PieceType.QUEEN, id, pieceColor, listOf(movement), NormalMovementBehaviour())
        }

        fun buildSithMaster(id: String, pieceColor: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val jumpMV = AndMV(listOf(JumpMV(1, 2), PositionIsFreeMV()))
            val diagonalMV = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), PositionIsFreeMV()))
            val eatHorizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatVerticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatDiagonalMV = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), EatMV()))
            val eatJumpMV = AndMV(listOf(JumpMV(1, 2), EatMV()))
            val movement = OrMV(
                listOf(
                    horizontalMV,
                    verticalMV,
                    diagonalMV,
                    eatHorizontalMV,
                    eatVerticalMV,
                    eatDiagonalMV,
                    jumpMV,
                    eatJumpMV
                )
            )
            return Piece(PieceType.KING, id, pieceColor, listOf(movement), NormalMovementBehaviour())
        }

        fun buildChecker(id: String, pieceColor: Color): Piece {
            val fowardDiagonalMV =
                AndMV(listOf(FowardDiagonalMV(), LimitMV(1), PositionIsFreeMV(), NotObligatedToEatValidator()))
            val eatMV = AndMV(listOf(FowardDiagonalMV(), LimitMV(2), EnemyInBetweenMV(), PositionIsFreeMV()))
            val movement = OrMV(listOf(fowardDiagonalMV, eatMV))
            return Piece(PieceType.PAWN, id, pieceColor, listOf(movement), CheckerMB())
        }

        fun buildCrowned(id: String, pieceColor: Color): Piece {
            val diagonalMV = AndMV(listOf(DiagonalMV(), LimitMV(1), PositionIsFreeMV(), NotObligatedToEatValidator()))
            val eatMv = AndMV(listOf(DiagonalMV(), LimitMV(2), EnemyInBetweenMV(), PositionIsFreeMV()))
            val movement = OrMV(listOf(diagonalMV, eatMv))
            return Piece(PieceType.QUEEN, id, pieceColor, listOf(movement), CrownedMb())
        }

        fun buildArchBishop(id: String, color: Color): Piece {
            val bishopMV = AndMV(listOf(DiagonalMV(), DiagonalPathIsFreeMV(), PositionIsFreeMV()))
            val eatbishopMV = AndMV(listOf(DiagonalMV(), EatMV(), DiagonalPathIsFreeMV()))
            val normalKnightmv = AndMV(listOf(JumpMV(1, 2), PositionIsFreeMV()))
            val eatKnightMV = AndMV(listOf(JumpMV(1, 2), EatMV()))
            val movement = OrMV(listOf(bishopMV, eatKnightMV, eatbishopMV, normalKnightmv))
            return Piece(PieceType.ARCHBISHOP, id, color, listOf(movement), NormalMovementBehaviour())
        }

        fun buildChancellor(id: String, color: Color): Piece {
            val horizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val verticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), PositionIsFreeMV()))
            val jumpMV = AndMV(listOf(JumpMV(1, 2), PositionIsFreeMV()))
            val eatHorizontalMV = AndMV(listOf(HorizontalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatVerticalMV = AndMV(listOf(VerticalMV(), HorizontalOrVerticalPathsAreFreeMV(), EatMV()))
            val eatJumpMV = AndMV(listOf(JumpMV(1, 2), EatMV()))
            val movement = OrMV(
                listOf(
                    horizontalMV,
                    verticalMV,
                    eatHorizontalMV,
                    eatVerticalMV,
                    jumpMV,
                    eatJumpMV
                )
            )
            return Piece(PieceType.CHANCELLOR, id, color, listOf(movement), NormalMovementBehaviour())
        }
    }
}