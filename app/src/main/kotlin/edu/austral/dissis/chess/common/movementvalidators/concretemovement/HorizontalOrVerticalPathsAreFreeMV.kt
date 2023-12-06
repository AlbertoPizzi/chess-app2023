package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class HorizontalOrVerticalPathsAreFreeMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
        val difRow: Int = (movement.finalpos.column - movement.initpos.column)
        val difCol: Int = (movement.finalpos.row - movement.initpos.row)
        val dif: Int = abs(difRow) + abs(difCol)

        for (i in 1..dif - 1 ) {
            val path = calculatePathPosition(movement.initpos, movement.finalpos, i, dif)
            if (board.getPositionMap()[path] != null) {
                return FailureResult("There's a piece in the path")
            }
        }
        return SuccessfulResult("The path is free")
    }

    private fun calculatePathPosition(initPos: Position, finalPos: Position, i: Int, dif: Int): Position {
        return Position(
            initPos.column + i * ((finalPos.column - initPos.column) / dif),
            initPos.row + i * ((finalPos.row - initPos.row) / dif)
        )
    }
}