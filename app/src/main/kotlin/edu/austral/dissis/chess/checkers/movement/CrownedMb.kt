package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.movementvalidators.behaviour.NormalMovementBehaviour
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.DiagonalMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EnemyInBetweenMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.movementvalidators.behaviour.MovementBehaviour
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.LimitMV
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class CrownedMb : MovementBehaviour {
    private val normalDiagonalMv = AndMV(
        listOf(
            DiagonalMV(),
            LimitMV(1),
            EmptySquareMV(),
            NotObligatedToEatValidator()
        )
    )

    private val basicCheckersValidator = CheckersBasicMV()

    val eatDiagonalMv = AndMV(
        listOf(
            DiagonalMV(),
            LimitMV(2),
            EnemyInBetweenMV(),
            EmptySquareMV(),
        )
    )

    override fun move(game: Game, movement: Movement): Game {
        if (isAnNormalMovement(movement, game)) {
            return NormalMovementBehaviour().move(game, movement)
        }
        return applyEatMovement(movement, game)
    }

    private fun isAnNormalMovement(movement: Movement, game: Game): Boolean {
        return (normalDiagonalMv.validateMovement(game, movement) is SuccessfulResult)
    }

    private fun isAnEatMovement(movement: Movement, game: Game): Boolean {
        return (eatDiagonalMv.validateMovement(game, movement) is SuccessfulResult)
    }

    private fun applyEatMovement(movement: Movement, gameState: Game): Game {
        val newGameState = NormalMovementBehaviour().move(gameState, movement)
        val auxX = (movement.finalpos.column - movement.initpos.column)
        val auxY = (movement.finalpos.row - movement.initpos.row)

        val stepX = if (auxX > 0) 1 else -1
        val stepY = if (auxY > 0) 1 else -1

        val intermediatePosition = Position(
            movement.initpos.column + 1 * stepX,
            movement.initpos.row + 1 * stepY
        )

        var newPieceMap = newGameState.getGameState().getPositionMap().toMutableMap()
        newPieceMap.remove(intermediatePosition)

        var afterEatingGs = newGameState.copy(state = newGameState.getGameState().copy(board = BoardFactory.updateBoard(newPieceMap, newGameState.getGameState().board)))
        val newPossibleMovement = canPieceStillEat(afterEatingGs, afterEatingGs.getGameState().getPiece(movement.finalpos))

        if (basicCheckersValidator.validateMovement(afterEatingGs, newPossibleMovement) is SuccessfulResult) {
            afterEatingGs = applyEatMovement(newPossibleMovement, afterEatingGs)
        }

        return afterEatingGs
    }

    private fun canPieceStillEat(game: Game, actualPiece: Piece): Movement {
        val gameState = game.getGameState()
        val piecePosition = gameState.getPositionByPieceID(actualPiece.id)!!
        for (i in 1..gameState.board.getRowSize()) {
            for (j in 1..gameState.board.getRowSize()) {
                val toPosition = Position(i, j)
                val movement = Movement(piecePosition, toPosition)
                if (actualPiece.mv[0].validateMovement(game, movement) is SuccessfulResult) {
                    if (isAnEatMovement(movement, game)) {
                        return movement
                    }
                }
            }
        }
        val outOfBoundPosition = Position(-1, -1)
        return Movement(outOfBoundPosition, outOfBoundPosition)
    }
}