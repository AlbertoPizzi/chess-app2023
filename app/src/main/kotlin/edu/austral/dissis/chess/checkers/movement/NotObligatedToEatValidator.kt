package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.DiagonalMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EnemyInBetweenMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.piece.PieceType
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class NotObligatedToEatValidator : MovementValidator {
    private val eatDiagonalMvChecker = AndMV(
        listOf(
            LimitMovementValidator(2),
            EnemyInBetweenMV(),
            EmptySquareMV(),
            FowardDiagonalMovementValidator()
        )
    )

    private val eatDiagonalMvCrowned = AndMV(
        listOf(
            LimitMovementValidator(2),
            EnemyInBetweenMV(),
            EmptySquareMV(),
            DiagonalMV()
        )
    )

    private val basicCheckersValidator = CheckersBasicMV()

    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val pieceList = gameState.getPositionMap().filter { it.value.pieceColor == gameState.getActualColor() }
        for ((piecePosition, piece) in pieceList) {
            for (toPosition in generateMovementList(gameState)) {
                val newMovement = Movement(piecePosition, toPosition)
                if (basicCheckersValidator.validateMovement(gameState, newMovement) is SuccessfulResult) {
                    if (piece.type.equals(PieceType.PAWN)) {
                        if (eatDiagonalMvChecker.validateMovement(gameState, newMovement) is SuccessfulResult) {
                            return FailureResult("You have to eat")
                        }
                    } else {
                        if (eatDiagonalMvCrowned.validateMovement(gameState, newMovement) is SuccessfulResult) {
                            return FailureResult("You have to eat")
                        }
                    }
                }
            }
        }
        return SuccessfulResult("Valid movement")
    }


    private fun generateMovementList(gameState: GameState): List<Position> {
        val positionList = mutableListOf<Position>()
        for (i in 1..gameState.board.getColSize()) {
            for (j in 1..gameState.board.getRowSize()) {
                positionList.add(Position(i, j))
            }
        }
        val list = positionList
        return list
    }
}