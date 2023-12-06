package edu.austral.dissis.chess.common.movementvalidators.concretemovement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import kotlin.math.abs

class DiagonalPathIsFreeMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.board
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