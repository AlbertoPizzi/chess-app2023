package common

import edu.austral.dissis.chess.gui.*
import common.board.Board
import common.board.Position
import mychess.game.GameState
import common.movementvalidators.Movement
import common.piece.Color
import common.piece.Piece
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class Adapter {
    private fun positionAdapter(position: Position): edu.austral.dissis.chess.gui.Position {
        return edu.austral.dissis.chess.gui.Position(position.row, position.column)
    }

    fun colorAdapter(color: Color): PlayerColor {
        return if (color == Color.WHITE) PlayerColor.WHITE
        else PlayerColor.BLACK
    }

    fun chessPieceAdapter(board: Board, piece: Piece): ChessPiece {
        return ChessPiece(
            piece.getId(), colorAdapter(piece.getPieceColor()), positionAdapter(board.getPositionByPiece(piece)),
            piece.getPieceType().toString().lowercase()
        )
    }

    private fun moveAdapter(movement: Movement): Move {
        return Move(positionAdapter(movement.initpos), positionAdapter(movement.finalpos))
    }

    fun pieceListAdapter(board: Board): List<ChessPiece> {
        val pieceList: List<Piece> = board.getPositionMap().values.toList()
        val chessPieceList: MutableList<ChessPiece> = mutableListOf()
        pieceList.forEach { piece: Piece ->
            chessPieceList.add(chessPieceAdapter(board, piece))
        }
        return chessPieceList.toList()
    }

    private fun adaptBoardSize(board: Board): BoardSize {
        return BoardSize(board.getRowSize(), board.getColSize())
    }

    private var states: MutableList<GameState> = mutableListOf()

    fun saveHistory(gameState: GameState) {
        states.add(gameState)
    }

    fun getLastState(): GameState {
        return states.last()
    }

    fun adaptGameStateToInitialState(gameState: GameState): InitialState {
        val board: Board = gameState.getBoardHistory().last()
        val boardSize = adaptBoardSize(board)
        val chessPieces = pieceListAdapter(board)
        val playerColor = colorAdapter(gameState.getTurnManager().getCurrentPlayer())
        return InitialState(boardSize, chessPieces, playerColor)
    }

    fun adaptMoveResult(result: ResultValidator): MoveResult {
        val gameState: GameState = states.last()
        val board: Board = gameState.getBoardHistory().last()
        val chessPieces = pieceListAdapter(board)
        val playerColor = colorAdapter(gameState.getTurnManager().getCurrentPlayer())
        return when (result) {
            is FailureResult -> InvalidMove(result.message)
            is SuccessfulResult -> NewGameState(chessPieces, playerColor)
            is common.result.GameOver -> GameOver(if (playerColor == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE)
        }
    }

    fun translateMovement(move: Move): Movement {
        return Movement(Position(move.from.column, move.from.row), Position(move.to.column, move.to.row))
    }

}