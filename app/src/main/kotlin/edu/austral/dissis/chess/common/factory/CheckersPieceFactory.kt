package edu.austral.dissis.chess.common.factory

import edu.austral.dissis.chess.checkers.movement.CheckerMB
import edu.austral.dissis.chess.checkers.movement.CrownedMb
import edu.austral.dissis.chess.checkers.movement.NotObligatedToEatValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.*
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.OrMV
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.piece.PieceType

class CheckersPieceFactory {
    companion object{
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
    }
}