package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.result.ResultValidator

class MaxMV(private val max: Int, private val pieceID: String) : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        return TODO("you gotta do this")
    }

}