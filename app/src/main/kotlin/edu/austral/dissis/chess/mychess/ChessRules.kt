package edu.austral.dissis.chess.mychess

import edu.austral.dissis.chess.common.Adapter
import edu.austral.dissis.chess.common.rules.Rules
import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.BoardType
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.game.ClassicTurn
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.game.TurnManager
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluator
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.mychess.movement.ChessMV
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.PieceMover
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.mychess.state.ChessStateEvaluator

class ChessRules {
//    :
//} Rules{
//    private val adapter = Adapter()
//    private val pieceMover = PieceMover()
//    private val ChessStateEvaluator: StateEvaluator = ChessStateEvaluator()
//    private val chessMV: MovementValidator = ChessMV()
//    private var gameState: GameState = init()
////TODO: esto tiene que ser un constructor
//
//    override fun init(): GameState {
//        val board = BoardFactory.createNewBoard(BoardType.CHESS)
//        val boardHistory: List<Board> = createHistoryFromBoard(board)
//        val turnManager: TurnManager = ClassicTurn(Color.WHITE)
//        val gameState = GameState(turnManager, boardHistory)
//        gameState.saveHistory(gameState)
//        return gameState
//    }
//
//    override fun getAdapter(): Adapter {
//        return adapter
//    }
//
//    override fun applyMove(move: Move): MoveResult {
//        val initPosition: Position = Position(move.from.column, move.from.row)
//        val finalPosition: Position = Position(move.to.column, move.to.row)
//        val board = gameState.getBoardHistory().last()
//        if (isMovementFailure(Movement(initPosition, finalPosition))) {
//            return InvalidMove(invalidMovementDescription(Movement(initPosition, finalPosition)))
//        }
//        val pieceToMove = board.getPieceByPosition(initPosition)!!
//        if (!checkCurrentTurn(pieceToMove.pieceColor)) {
//            return InvalidMove("It's ${gameState.getTurnManager().getCurrentPlayer()}'s turn!")
//        }
//        val newBoard = pieceMover.moveTo(pieceToMove, finalPosition, gameState)
//        val history: List<Board> = createHistoryFromBoard(newBoard)
//        val advanceTurn = gameState.getTurnManager().nextTurn()
//        gameState.saveHistory(GameState(advanceTurn, history,,))
//        return when (stateEvaluatorResult(gameState.getLastState())) {
//            is InProgressStateResult -> {
//                gameState = gameState.copy(turnManager = advanceTurn, boardHistory = history)
//                adapter.adaptGameState(ChessPromotionStrategy().promote(gameState))
////                adapter.adaptGameState(gameState)
//            }
//            is WinStateResult -> GameOver(adapter.colorAdapter((stateEvaluatorResult(gameState.getLastState()) as WinStateResult).winner))
//        }
//    }
//
//
//    private fun isMovementFailure(move: Movement): Boolean {
//        return chessMV.validateMovement(gameState, move) is FailureResult
//    }
//
//    private fun invalidMovementDescription(move: Movement): String {
//        return (chessMV.validateMovement(gameState, move) as FailureResult).message
//    }
//
//    private fun stateEvaluatorResult(gs: GameState): StateEvaluatorResult {
//        return ChessStateEvaluator.validate(gs)
//    }
//
//    private fun checkCurrentTurn(color: Color): Boolean {
//        val turnManager = gameState.getTurnManager()
//        return color == turnManager.getCurrentPlayer()
//    }
//
//    private fun createHistoryFromBoard(board: Board): List<Board> {
//        val boardHistory: MutableList<Board> = mutableListOf()
//        boardHistory.add(board)
//        return boardHistory
//    }
}
