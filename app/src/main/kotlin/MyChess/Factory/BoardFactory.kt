package Factory

import board.Board
import board.BoardType
import board.Position
import board.RegularBoard
import piece.Piece

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
