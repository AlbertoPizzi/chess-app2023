package commons.movementvalidators.concretemovement

import mychess.board.Board
import mychess.board.Position
import commons.movementvalidators.Movement
import commons.movementvalidators.MovementValidator
import commons.piece.Piece
import commons.result.FailureResult
import commons.result.ResultValidator
import commons.result.SuccessfulResult
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