package edu.austral.dissis.chess.common.movementvalidators.logicalmovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class AndMV(val mvList: List<MovementValidator>) : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        for (mv in mvList) {
            val auxResult = mv.validateMovement(game, movement)
            if (auxResult is FailureResult) {
                return auxResult
            }
        }
        return SuccessfulResult("It is a valid move!")
    }
}