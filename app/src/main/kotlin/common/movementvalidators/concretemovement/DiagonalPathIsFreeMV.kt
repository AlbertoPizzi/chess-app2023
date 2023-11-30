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

class DiagonalPathIsFreeMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
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
                return FailureResult("There's a piece in the path")
            }
        }
        return SuccessfulResult("The path is free")
    }
}