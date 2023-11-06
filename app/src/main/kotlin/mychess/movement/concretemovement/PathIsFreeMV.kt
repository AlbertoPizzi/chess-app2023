package mychess.movement.concretemovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.board.Position
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.piece.Piece
import mychess.piece.PieceType
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
import kotlin.math.abs

class PathIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val pieceActualPosition : Position = movement.initpos
        val difRow: Int = abs(pieceActualPosition.row - movement.finalpos.row)
        var path: Position
        for (i in 1..difRow) {
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