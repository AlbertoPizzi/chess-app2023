package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game
import kotlin.math.abs

class EnemyInBetweenMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
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