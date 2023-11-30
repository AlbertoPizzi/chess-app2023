package mychess.movement.endvalidators

import common.board.Board
import common.piece.Piece
import common.piece.PieceType
import common.result.*

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