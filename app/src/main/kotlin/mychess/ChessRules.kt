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
import common.movementvalidators.ChessMV
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.PieceMover
import common.piece.Color
import common.piece.Piece
import common.result.FailureResult
import common.result.SuccessfulResult
import edu.austral.dissis.chess.gui.*
import mychess.movement.endvalidators.KingIsDeadValidator
import mychess.movement.endvalidators.MatchEndingValidator
import mychess.state.ChessStateEvaluator

class ChessRules : Rules {
    private val adapter = Adapter()
    private val pieceMover = PieceMover()
    private val kingIsDeadValidator: MatchEndingValidator = KingIsDeadValidator()
    private val ChessStateEvaluator: StateEvaluator = ChessStateEvaluator()
    private val chessMV: MovementValidator = ChessMV()
    private var gameState: GameState = init()


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
        if (isMovementSuccessful(Movement(initPosition, finalPosition))) {
            val pieceToMove = board.getPieceByPosition(initPosition)!!
            if(!checkCurrentTurn(pieceToMove.pieceColor)) return InvalidMove("It's ${gameState.getTurnManager().getCurrentPlayer()}'s turn!" )
            else{
                val newBoard = pieceMover.moveTo(pieceToMove , finalPosition , gameState)
                val history : List<Board> = createHistoryFromBoard(newBoard)
                val advanceTurn = gameState.getTurnManager().nextTurn()
                adapter.saveHistory(GameState(advanceTurn , history ))
                when(stateEvaluatorResult(adapter.getLastState())){
                    is InProgressStateResult -> {
                        gameState = gameState.copy(turnManager = advanceTurn , boardHistory = history)
                        return adapter.adaptGameState(gameState)
                    }
                    is WinStateResult -> return GameOver(adapter.colorAdapter((stateEvaluatorResult(adapter.getLastState()) as WinStateResult).winner))
                }

            }

        }
        return InvalidMove(invalidMovementDescription(Movement(initPosition , finalPosition)))
    }


    private fun isMovementSuccessful(move: Movement): Boolean {
        return chessMV.validateMovement(gameState, move) is SuccessfulResult
    }

    private fun invalidMovementDescription(move: Movement): String {
        return (chessMV.validateMovement(gameState, move) as FailureResult).message
    }

    private fun stateEvaluatorResult(gs: GameState): StateEvaluatorResult {
        return ChessStateEvaluator.validate(gs)
    }
    private fun checkCurrentTurn(color: Color): Boolean{
        val turnManager = gameState.getTurnManager()
        return color == turnManager.getCurrentPlayer()
    }

    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}

//if (!board.getPositionMap().containsKey(initPosition)) return InvalidMove("There's nothing there")
//        else {
//            val pieceToMove = board.getPieceByPosition(initPosition)!!
//            if (pieceToMove.pieceColor != turnManager.getCurrentPlayer()) {
//                return InvalidMove("It's " + turnManager.getCurrentPlayer() + "'s Turn!")
//            } else {
//                val newBoard: Board = pieceMover.moveTo(pieceToMove, finalPosition, gameState)
//                if (newBoard == gameState.getBoardHistory().last()) {
//                    return InvalidMove("Invalid movement for " +
//                            pieceToMove.id.takeWhile { it.isLetter() })
//                }

//                if (kingIsDeadValidator.validate(board) is GameOver
//                    && board.getBoardType() != BoardType.CHECKERS
//                ) {
//                    if (turnManager.getCurrentPlayer() == Color.WHITE) {
//                        return GameOver(adapter.colorAdapter(Color.BLACK))
//                    } else return GameOver(adapter.colorAdapter(Color.WHITE))
//                }
