package common.movementvalidators.concretemovement

import common.board.Position
import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class EnemyInBetweenMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        val auxX = (movement.finalpos.column - movement.initpos.column)
        val auxY = (movement.finalpos.row - movement.initpos.row)

        val stepX = if (auxX > 0) 1 else -1
        val stepY = if (auxY > 0) 1 else -1

        for (i in 1 until abs(auxX)) {
            val intermediatePosition = Position(
                movement.initpos.column + i * stepX,
                movement.initpos.row + i * stepY
            )
            if (board.getPositionMap().containsKey(intermediatePosition)) {
                if (board.getPositionMap()[intermediatePosition]!!.pieceColor != board.getPositionMap()[movement.initpos]!!.pieceColor) {
                    return SuccessfulResult("Enemy in between!")
                } else return FailureResult("Invalid movement")
            }
        }
        return FailureResult("Invalid movement")
    }
}