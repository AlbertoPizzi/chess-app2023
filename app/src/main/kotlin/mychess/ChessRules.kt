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
import common.gamestates.InProgressStateResult
import common.gamestates.StateEvaluator
import common.gamestates.StateEvaluatorResult
import common.gamestates.WinStateResult
import mychess.movement.ChessMV
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.PieceMover
import common.piece.Color
import common.result.FailureResult
import common.result.SuccessfulResult
import edu.austral.dissis.chess.gui.*
import mychess.state.ChessStateEvaluator

class ChessRules : Rules {
    private val adapter = Adapter()
    private val pieceMover = PieceMover()
    private val ChessStateEvaluator: StateEvaluator = ChessStateEvaluator()
    private val chessMV: MovementValidator = ChessMV()
    private var gameState: GameState = init()
//TODO: esto tiene que ser un constructor

    override fun init(): GameState {
        val board = BoardFactory.createNewBoard(BoardType.CHESS)
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
        val initPosition: Position = Position(move.from.column, move.from.row)
        val finalPosition: Position = Position(move.to.column, move.to.row)
        val board = gameState.getBoardHistory().last()
        if (isMovementFailure(Movement(initPosition, finalPosition))) {
            return InvalidMove(invalidMovementDescription(Movement(initPosition, finalPosition)))
        }
        val pieceToMove = board.getPieceByPosition(initPosition)!!
        if (!checkCurrentTurn(pieceToMove.pieceColor)) {
            return InvalidMove("It's ${gameState.getTurnManager().getCurrentPlayer()}'s turn!")
        }
        val newBoard = pieceMover.moveTo(pieceToMove, finalPosition, gameState)
        val history: List<Board> = createHistoryFromBoard(newBoard)
        val advanceTurn = gameState.getTurnManager().nextTurn()
        adapter.saveHistory(GameState(advanceTurn, history))
        return when (stateEvaluatorResult(adapter.getLastState())) {
            is InProgressStateResult -> {
                ChessPromoter().promote(gameState, Movement(initPosition, finalPosition))
                gameState = gameState.copy(turnManager = advanceTurn, boardHistory = history)
                adapter.adaptGameState(gameState)
            }

            is WinStateResult -> GameOver(adapter.colorAdapter((stateEvaluatorResult(adapter.getLastState()) as WinStateResult).winner))
        }
    }


    private fun isMovementFailure(move: Movement): Boolean {
        return chessMV.validateMovement(gameState, move) is FailureResult
    }

    private fun invalidMovementDescription(move: Movement): String {
        return (chessMV.validateMovement(gameState, move) as FailureResult).message
    }

    private fun stateEvaluatorResult(gs: GameState): StateEvaluatorResult {
        return ChessStateEvaluator.validate(gs)
    }

    private fun checkCurrentTurn(color: Color): Boolean {
        val turnManager = gameState.getTurnManager()
        return color == turnManager.getCurrentPlayer()
    }

    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}
