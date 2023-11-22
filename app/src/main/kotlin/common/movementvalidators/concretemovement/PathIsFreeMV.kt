package common.movementvalidators.concretemovement

import mychess.board.Board
import mychess.board.Position
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult
import kotlin.math.abs

class PathIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val pieceActualPosition : Position = movement.initpos
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
                return FailureResult("There's a piece in path!")
            }
        }
        return SuccessfulResult("there's no piece in path")
    }
}