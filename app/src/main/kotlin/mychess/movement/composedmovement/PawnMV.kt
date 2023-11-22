package mychess.movement.composedmovement

import mychess.board.Board
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.concretemovement.*
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class PawnMV : MovementValidator {
    private val verticalMV: MovementValidator = VerticalMV() //moves
    private val diagonalMV: MovementValidator = DiagonalMV() //eats
    private val eatMV: MovementValidator = EatMV()
    private val colorCheck: MovementValidator = ColorMV() //checks Color
    private val pathIsFreeMV: MovementValidator = PathIsFreeMV() //checks free path
    private val positionIsFreeMV: MovementValidator = PositionIsFreeMV() // checks if the final position is free
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        //check if movement is part of moveSet
        if (verticalMV.validateMovement(board, movement) is SuccessfulResult) {
            if (checkFirstMove(
                    board,
                    movement
                ) && movement.finalpos.row.equals(movement.initpos.row + (2 * checkWhiteMovement(board, movement)))
                && pathIsFreeMV.validateMovement(board, movement) is SuccessfulResult
                && positionIsFreeMV.validateMovement(board, movement) is SuccessfulResult
            ) {
                return SuccessfulResult("It's a valid first move!")
            }
            if (movement.finalpos.row.equals(movement.initpos.row + (1 * checkWhiteMovement(board, movement)))
                && positionIsFreeMV.validateMovement(board, movement) is SuccessfulResult
            ) {
                return SuccessfulResult("It's a valid move!")
            }
        }
        if (positionIsFreeMV.validateMovement(board, movement) is FailureResult) {
            if (diagonalMV.validateMovement(board, movement) is SuccessfulResult
                && eatMV.validateMovement(board, movement) is SuccessfulResult
            ) {
                if (movement.finalpos.row.equals(movement.initpos.row + (1 * checkWhiteMovement(board, movement))))
                    return SuccessfulResult("It's a valid move!")
            }
        }
        return FailureResult("It's not a valid move!")
    }

    fun checkFirstMove(board: Board, movement: Movement): Boolean {
        //White
        if (board.getPieceByPosition(movement.initpos)?.getPieceColor() == Color.WHITE
            && movement.initpos.row == 2
        ) {
            return true
        }
        //black
        if (board.getPieceByPosition(movement.initpos)?.getPieceColor() == Color.BLACK
            && movement.initpos.row == 7
        ) {
            return true
        }
        return false
    }

    fun checkWhiteMovement(board: Board, movement: Movement): Int {
        return if (board.getPieceByPosition(movement.initpos)?.getPieceColor() == Color.WHITE) {
            1
        } else return -1
    }

    fun checkFreeFinalPos(board: Board, finalpos: mychess.board.Position): ResultValidator {
        if (!board.getPositionMap().containsKey(finalpos)) {
            return SuccessfulResult("that's a free square")
        }
        return FailureResult("Position not free")
    }
}