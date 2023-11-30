package common.movementvalidators.concretemovement

import common.board.Position
import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class PathIsFreeMV : MovementValidator {
    val verticalMV = VerticalMV()
    val horizontalMV = HorizontalMV()
    val diagonalMV = DiagonalMV()
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        if(verticalMV.validateMovement(gameState , movement) is SuccessfulResult ||
            horizontalMV.validateMovement(gameState, movement) is SuccessfulResult){
            if(validateHorizontalAndVerticalIsFree(gameState, movement)){
                return SuccessfulResult("It is a valid move!")
            }
        }
        else if(diagonalMV.validateMovement(gameState, movement) is SuccessfulResult){
            if(validateDiagonalIsFree(gameState, movement)){
                return SuccessfulResult("It is a valid move!")
            }
        }
       return FailureResult("It is not a valid move!")
    }

    private fun validateDiagonalIsFree(gameState: GameState, movement: Movement): Boolean {
        val board = gameState.getBoardHistory().last()
        val pieceActualPosition: Position = movement.initpos
        val difRow: Int = abs(pieceActualPosition.row - movement.finalpos.row)
        var path: Position
        for (i in 1..difRow - 1) {
            path = Position(
                pieceActualPosition.column + i * ((movement.finalpos.column - pieceActualPosition.column)
                        / difRow),
                pieceActualPosition.row + i * ((movement.finalpos.row - pieceActualPosition.row)
                        / difRow)
            )
            val pieceInPath: Piece? = board.getPositionMap().get(path)
            if (pieceInPath != null) {
                return false
            }
        }
        return true
    }

    private fun validateHorizontalAndVerticalIsFree(gameState: GameState, movement: Movement): Boolean {
        val board = gameState.getBoardHistory().last()
        val difRow: Int = (movement.finalpos.column - movement.initpos.column)
        val difCol: Int = (movement.finalpos.row - movement.initpos.row)
        val dif: Int = abs(difRow) + abs(difCol)

        for (i in 1..dif - 1 ) {
            val path = calculatePathPosition(movement.initpos, movement.finalpos, i, dif)
            if (board.getPositionMap()[path] != null) {
                return false
            }
        }
        return true
    }
    private fun calculatePathPosition(initPos: Position, finalPos: Position, i: Int, dif: Int): Position {
        return Position(
            initPos.column + i * ((finalPos.column - initPos.column) / dif),
            initPos.row + i * ((finalPos.row - initPos.row) / dif)
        )
    }
}