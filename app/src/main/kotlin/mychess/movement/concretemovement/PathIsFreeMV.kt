package mychess.movement.concretemovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.board.Position
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.piece.PieceType
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
import kotlin.math.abs

class PathIsFreeMV : MovementValidator {
    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        val difRow: Int = (movement.initpos.row - movement.finalpos.row)
        val difCol: Int = (movement.initpos.column - movement.finalpos.column)

        if(board.getPieceByPosition(movement.initpos).getPieceType().equals(PieceType.BISHOP) ||
            board.getPieceByPosition(movement.initpos).getPieceType().equals(PieceType.QUEEN)){
            var path: Position
            for(i in 1 until abs(difRow)){
                TODO("Not yet implemented")
            }
        }
        return SuccessfulResult("TODO")
    }
}