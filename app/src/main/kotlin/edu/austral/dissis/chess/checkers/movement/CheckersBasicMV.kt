package edu.austral.dissis.chess.checkers.movement

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.CurrentPlayerMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.EmptySquareMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.NotTheSamePositionMV
import edu.austral.dissis.chess.common.movementvalidators.concretemovement.PositionIsFreeMV
import edu.austral.dissis.chess.common.movementvalidators.logicalmovement.AndMV
import edu.austral.dissis.chess.common.result.ResultValidator


class CheckersBasicMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val basicMovement =
            AndMV(listOf(EmptySquareMV(), CurrentPlayerMV(), NotTheSamePositionMV(), PositionIsFreeMV()))
        return basicMovement.validateMovement(gameState, movement)
    }
}
