package mychess.movement

import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.concretemovement.EmptySquareMV
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import mychess.movement.composedmovement.NotInCheckMV

class ChessMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        when(basicCheckers(gameState, movement)){
             true -> {
                return board.getPositionMap()[movement.initpos]!!.movement[0].validateMovement(gameState, movement)
            }
             false -> {
                return FailureResult("Invalid Movement")
            }
        }
    }
    private fun basicCheckers(gameState: GameState , movement: Movement) : Boolean{
       return (EmptySquareMV().validateMovement(gameState , movement) is SuccessfulResult)
               && (NotInCheckMV().validateMovement(gameState , movement) is SuccessfulResult)
    }
}