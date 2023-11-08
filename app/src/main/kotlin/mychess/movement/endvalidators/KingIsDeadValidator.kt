package mychess.movement.endvalidators

import mychess.board.Board
import mychess.piece.Piece
import mychess.piece.PieceType
import mychess.result.*

class KingIsDeadValidator : MatchEndingValidator {

    override fun validate(board: Board): ResultValidator {
        if(getKings(board).size != 1){
            return FailureResult("Match is still going!")
        }
        return GameOver("Game Over")
    }
    private fun getKings(board : Board) : List<Piece>{
        var kings : MutableList<Piece> = mutableListOf()
        for (piece : Piece in board.getPieces()){
            if(piece.getPieceType().equals(PieceType.KING)){
                kings.add(piece)
            }
        }
        return kings
    }
}