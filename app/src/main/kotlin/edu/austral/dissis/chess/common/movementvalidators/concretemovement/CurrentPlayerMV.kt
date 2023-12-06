package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult

class CurrentPlayerMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        if(gameState.getPositionMap().get(movement.initpos)!!.pieceColor === gameState.getActualColor()){
            return SuccessfulResult("It's valid movement")
        }
        return FailureResult("The piece is not from actual turn colour")
    }
}