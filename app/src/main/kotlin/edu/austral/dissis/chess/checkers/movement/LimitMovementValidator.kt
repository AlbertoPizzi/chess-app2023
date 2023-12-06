package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class LimitMovementValidator(
    val limit: Int
) : MovementValidator {

    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val difCol = abs(movement.finalpos.column - movement.initpos.column)
        val difRow = abs(movement.finalpos.row - movement.initpos.row)
        if(difCol <= limit &&  difRow <= limit){
            return SuccessfulResult("")
        }
        return FailureResult("Invalid movement")
    }
}