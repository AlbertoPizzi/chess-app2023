package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.CurrentPlayerMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.mychess.movement.composedmovement.NotInCheckMV

class CheckersBasicMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
       val basicMovement = AndMV(listOf(EmptySquareMV() , CurrentPlayerMV()))
        return basicMovement.validateMovement(gameState , movement)
    }
}
