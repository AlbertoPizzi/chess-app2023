package edu.austral.dissis.chess.common.movementvalidators

import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.rules.Game

interface MovementValidator {
    fun validateMovement(game: Game, movement: Movement): ResultValidator
}