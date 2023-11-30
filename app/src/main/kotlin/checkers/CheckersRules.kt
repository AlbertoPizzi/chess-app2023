package checkers

import common.Adapter
import common.Rules
import common.board.Board
import common.board.BoardType
import common.factory.BoardFactory
import common.game.ClassicTurn
import common.game.GameState
import common.game.TurnManager
import common.piece.Color
import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult

class CheckersRules : Rules {
    private val adapter = Adapter()
    override fun init(): GameState {
        val board = BoardFactory.createNewBoard(BoardType.CHECKERS)
        val boardHistory: List<Board> = createHistoryFromBoard(board)
        val turnManager: TurnManager = ClassicTurn(Color.WHITE)
        val gameState = GameState(turnManager, boardHistory)
        adapter.saveHistory(gameState)

        return gameState
    }

    override fun getAdapter(): Adapter {
        return adapter
    }

    override fun applyMove(move: Move): MoveResult {
        TODO("Not yet implemented")
    }

    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}