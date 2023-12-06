package edu.austral.dissis.chess.checkers

import edu.austral.dissis.chess.checkers.movement.CheckersMV
import edu.austral.dissis.chess.checkers.state.CheckersStateEvaluator
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
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.PieceMover
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.gui.*

class CheckersRules
//    : Rules {
//    private val adapter = Adapter()
//    private var gameState: GameState = init()
//    private val pieceMover = PieceMover()
//    private val checkersMV = CheckersMV()
//    override fun init(): GameState {
//        val board = BoardFactory.createNewBoard(BoardType.CHECKERS)
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
//        val board = gameState.board
//        if (isMovementSuccessful(Movement(initPosition, finalPosition))) {
//            val pieceToMove = board.getPieceByPosition(initPosition)!!
//            if (!checkCurrentTurn(pieceToMove.pieceColor)) return InvalidMove(
//                "It's ${gameState.getTurnManager().getCurrentPlayer()}'s turn!"
//            )
//            else {
//                val newBoard = pieceMover.moveTo(pieceToMove, finalPosition, gameState)
//                val history: List<Board> = createHistoryFromBoard(newBoard)
//                val advanceTurn = gameState.getTurnManager().nextTurn()
//                gameState.saveHistory(GameState(advanceTurn, history))
//                when (stateEvaluatorResult(gameState.getLastState())) {
//                    is InProgressStateResult -> {
//                        gameState = gameState.copy(turnManager = advanceTurn, boardHistory = history)
//                        return adapter.adaptGameState(gameState)
//                    }
//
//                    is WinStateResult -> return GameOver(adapter.colorAdapter((stateEvaluatorResult(gameState.getLastState()) as WinStateResult).winner))
//                }
//
//            }
//
//        }
//        return InvalidMove(invalidMovementDescription(Movement(initPosition, finalPosition)))
//    }
//
//    private fun isMovementSuccessful(move: Movement): Boolean {
//        return checkersMV.validateMovement(gameState, move) is SuccessfulResult
//    }
//
//    private fun invalidMovementDescription(move: Movement): String {
//        return (checkersMV.validateMovement(gameState, move) as FailureResult).message
//    }
//
//    private fun stateEvaluatorResult(gs: GameState): StateEvaluatorResult {
//        return CheckersStateEvaluator().validate(gs)
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
//}
//TODO