package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game
import kotlin.math.abs

class DiagonalMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        if (!board.isInBounds(movement.finalpos)) {
            return FailureResult("Movement is out of bounds!")
        }
        val difRow: Int = abs(movement.initpos.row - movement.finalpos.row)
        val difcol: Int = abs(movement.initpos.column - movement.finalpos.column)
        if (difRow == difcol) {
            return SuccessfulResult("It is a diagonal Movement!")
        }
        return FailureResult("It isn't a Diagonal Movement!")
    }
}