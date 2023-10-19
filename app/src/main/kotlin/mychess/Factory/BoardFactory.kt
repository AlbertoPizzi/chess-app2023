package mychess.Factory

import mychess.board.Board
import mychess.board.BoardType
import mychess.board.Position
import mychess.board.RegularBoard
import mychess.piece.Piece

class BoardFactory {
    companion object {
        fun createNewBoard(boardMap: Map<Position, Piece>, board: Board): Board {
            if (board.getBoardType().equals(BoardType.REGULAR)) {
                return createNewRegularBoard()
            }
            return board
        }

        fun createNewRegularBoard(): RegularBoard {
            TODO("not yet implemented")
        }
    }
}
