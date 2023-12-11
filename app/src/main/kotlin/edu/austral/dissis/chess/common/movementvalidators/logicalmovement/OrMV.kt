package edu.austral.dissis.chess.common.movementvalidators.logicalmovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class OrMV(private val mvList: List<MovementValidator>) : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        for (mv in mvList) {
            val auxResult = mv.validateMovement(game, movement)
            if (auxResult is SuccessfulResult) {
                return auxResult
            }
        }
        return FailureResult("It is not a valid move!")
    }
}