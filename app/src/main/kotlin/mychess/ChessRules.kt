package mychess

import common.Adapter
import common.Rules
import common.board.Board
import common.board.BoardType
import common.board.Position
import common.factory.BoardFactory
import common.game.ClassicTurn
import common.game.GameState
import common.game.TurnManager
import common.movementvalidators.PieceMover
import common.piece.Color
import common.result.GameOver
import edu.austral.dissis.chess.gui.*
import mychess.movement.endvalidators.KingIsDeadValidator
import mychess.movement.endvalidators.MatchEndingValidator

class ChessRules : Rules {
    private val adapter = Adapter()
    private val pieceMover = PieceMover()
    private val kingIsDeadValidator: MatchEndingValidator = KingIsDeadValidator()

    override fun init(): InitialState {
        val board = BoardFactory.createNewBoard(BoardType.CHESS)
        val boardHistory: List<Board> = createHistoryFromBoard(board)
        val turnManager: TurnManager = ClassicTurn(Color.WHITE)
        val gameState = GameState(turnManager, boardHistory)
        adapter.saveHistory(gameState)
        return adapter.adaptGameStateToInitialState(gameState)
    }

    override fun getAdapter(): Adapter {
        return adapter
    }

    override fun applyMove(move: Move): MoveResult {
        val initPosition: Position = Position(move.from.column, move.from.row)
        val finalPosition: Position = Position(move.to.column, move.to.row)
        val gameState: GameState = adapter.getLastState()
        val board: Board = gameState.getBoardHistory().last()
        val turnManager: TurnManager = gameState.getTurnManager()
        if (!board.getPositionMap().containsKey(initPosition)) return InvalidMove("There's nothing there")
        else {
            val pieceToMove = board.getPieceByPosition(initPosition)!!
            if (pieceToMove.pieceColor != turnManager.getCurrentPlayer()) {
                return InvalidMove("It's " + turnManager.getCurrentPlayer() + "'s Turn!")
            } else {
                val newBoard: Board = pieceMover.moveTo(pieceToMove, finalPosition, gameState.getBoardHistory().last())
                if (newBoard == gameState.getBoardHistory().last()) {
                    return InvalidMove("Invalid movement for " +
                            pieceToMove.id.takeWhile { it.isLetter() })
                }
                if (kingIsDeadValidator.validate(board) is GameOver
                    && board.getBoardType() != BoardType.CHECKERS ) {
                    if (turnManager.getCurrentPlayer() == Color.WHITE) {
                        return GameOver(adapter.colorAdapter(Color.BLACK))
                    } else return GameOver(adapter.colorAdapter(Color.WHITE))
                }

                val history: List<Board> = createHistoryFromBoard(newBoard)
                val advanceTurn = turnManager.nextTurn(pieceToMove.pieceColor)

                adapter.saveHistory(GameState(advanceTurn, history))

                return NewGameState(
                    adapter.pieceListAdapter(newBoard),
                    adapter.colorAdapter(advanceTurn.getCurrentPlayer())
                )
            }
        }
    }
    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}