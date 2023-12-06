package edu.austral.dissis.chess.mychess.movement.endvalidators

import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.piece.Piece
import edu.austral.dissis.chess.common.piece.PieceType
import edu.austral.dissis.chess.common.result.*

class KingIsDeadValidator : MatchEndingValidator {

    override fun validate(board: Board): ResultValidator {
        if (getKings(board).size != 1) {
            return FailureResult("Match is still going!")
        }
        return GameOver("Game Over")
    }

    private fun getKings(board: Board): List<Piece> {
        var kings: MutableList<Piece> = mutableListOf()
        for (piece: Piece in board.getPieces()) {
            if (piece.type.equals(PieceType.KING)) {
                kings.add(piece)
            }
        }
        return kings
    }
}