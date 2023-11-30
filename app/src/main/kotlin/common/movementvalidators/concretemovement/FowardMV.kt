package common.movementvalidators.concretemovement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class FowardMV : MovementValidator {
    private val verticalMV: MovementValidator = VerticalMV()
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        when (board.getPieceByPosition(movement.initpos)!!.pieceColor) {
            Color.WHITE -> {
                if (verticalMV.validateMovement(gameState, movement) is SuccessfulResult
                    && movement.finalpos.row > movement.initpos.row
                ) {
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("invalid movment")
            }

            Color.BLACK -> {
                if (verticalMV.validateMovement(gameState, movement) is SuccessfulResult
                    && movement.finalpos.row < movement.initpos.row
                ) {
                    return SuccessfulResult("Valid movement")
                }
                return FailureResult("Invalid movement")
            }
        }
    }
}