package mychess.movement.composedmovement

import mychess.board.Board
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult

class QueenMV : MovementValidator {
    private val rookMV : MovementValidator = RookMV()
    private val bishopMV : MovementValidator = BishopMV()
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(rookMV.validateMovement(board , movement) is SuccessfulResult){
            return SuccessfulResult("Rook Movement is valid")
        }
        else if(bishopMV.validateMovement(board, movement) is SuccessfulResult){
            return SuccessfulResult("Bishop Movement is valid")
        }
        else return FailureResult("Movement not valid")
    }
}