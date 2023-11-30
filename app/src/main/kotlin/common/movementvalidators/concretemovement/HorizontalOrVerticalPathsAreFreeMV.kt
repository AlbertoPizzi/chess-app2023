package common.movementvalidators.concretemovement

import common.board.Position
import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class HorizontalOrVerticalPathsAreFreeMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
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