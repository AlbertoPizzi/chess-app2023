package common

import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.BoardType
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.board.RegularBoard
import edu.austral.dissis.chess.common.game.ClassicTurn
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.game.TurnManager
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece
import org.testng.annotations.Test

class ChessPieceFactoryTest {

    fun generateBoard(x: Int, y: Int): Board {
        val emptyBoard: Map<Position, Piece> = emptyMap()
        val emptyList: List<Position> = emptyBoard.keys.toList()
        return RegularBoard(BoardType.EMPTY_BOARD, x, y, emptyBoard, emptyList)
    }
//    fun generateGamestate(piece: Piece): GameState {
//        val turnManager: TurnManager = ClassicTurn(Color.WHITE)
//        val boardHistory: List<Board> = listOf(generateBoard(8, 8))
//        return GameState(turnManager, boardHistory,,)
//    }

    @Test
    fun Test_001_CreatesAPiece(){

    }
}