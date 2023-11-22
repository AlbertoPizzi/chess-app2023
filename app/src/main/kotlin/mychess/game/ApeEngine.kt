package mychess.game

import edu.austral.dissis.chess.gui.*
import common.Adapter
import mychess.board.Board
import mychess.board.BoardType
import mychess.board.Position
import mychess.factory.BoardFactory
import common.movementvalidators.PieceMover
import mychess.movement.endvalidators.KingIsDeadValidator
import mychess.movement.endvalidators.MatchEndingValidator
import common.piece.Color
import common.result.GameOver

class ApeEngine : GameEngine {
    //    private val game  = Game()
    private val adapter = Adapter()
    private val pieceMover = PieceMover()
    private val kingIsDeadValidator : MatchEndingValidator = KingIsDeadValidator()

    override fun init(): InitialState {
        val board = BoardFactory.createNewBoard(BoardType.REGULAR)
        val boardHistory: List<Board> = createHistoryFromBoard(board)
        val turnManager: TurnManager = ClassicTurn(Color.WHITE)
        val gameState = GameState(turnManager, boardHistory)
        adapter.saveHistory(gameState)
        return adapter.adaptGameStateToInitialState(gameState)
    }

    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
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
            if (pieceToMove.getPieceColor() != turnManager.getCurrentPlayer()) {
                return InvalidMove("It's " + turnManager.getCurrentPlayer() + "'s Turn!")
            } else {
                val newBoard: Board = pieceMover.moveTo(pieceToMove, finalPosition, gameState.getBoardHistory().last())
                if (newBoard == gameState.getBoardHistory().last()) {
                    return InvalidMove("Invalid movement for " +
                            pieceToMove.getId().takeWhile { it.isLetter() })
                }
                if(kingIsDeadValidator.validate(board) is GameOver){
                    if(turnManager.getCurrentPlayer() == Color.WHITE){
                        return GameOver(adapter.colorAdapter(Color.BLACK))
                    }
                    else return GameOver(adapter.colorAdapter(Color.WHITE))
                }

                val history: List<Board> = createHistoryFromBoard(newBoard)
                val advanceTurn = turnManager.nextTurn(pieceToMove.getPieceColor())

                adapter.saveHistory(GameState(advanceTurn, history))

                return NewGameState(
                    adapter.pieceListAdapter(newBoard),
                    adapter.colorAdapter(advanceTurn.getCurrentPlayer())
                )
            }
        }
    }


}