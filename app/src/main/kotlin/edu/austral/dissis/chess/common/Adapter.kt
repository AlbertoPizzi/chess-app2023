package edu.austral.dissis.chess.common

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece

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
            piece.id, colorAdapter(piece.pieceColor), positionAdapter(board.getPositionByPiece(piece)),
            piece.type.toString().lowercase()
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


    fun adaptGameStateToInitialState(gameState: GameState): InitialState {
        val board: Board = gameState.board
        val boardSize = adaptBoardSize(board)
        val chessPieces = pieceListAdapter(board)
        val playerColor = colorAdapter(gameState.turnManager.getCurrentPlayer())
        return InitialState(boardSize, chessPieces, playerColor)
    }
    fun adaptGameState(gameState: GameState) : NewGameState{
        val pieces = pieceListAdapter(gameState.history.boardHistory.last())
        val playerColor = colorAdapter(gameState.turnManager.getCurrentPlayer())
        return NewGameState(pieces, playerColor)
    }

//    fun adaptMoveResult(result: ResultValidator): MoveResult {
//        val gameState: GameState = states.last()
//        val board: Board = gameState.getBoardHistory().last()
//        val chessPieces = pieceListAdapter(board)
//        val playerColor = colorAdapter(gameState.getTurnManager().getCurrentPlayer())
//        return when (result) {
//            is FailureResult -> InvalidMove(result.message)
//            is SuccessfulResult -> NewGameState(chessPieces, playerColor)
//            is edu.austral.dissis.chess.common.result.GameOver -> GameOver(if (playerColor == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE)
//        }
//    }

    fun translateMovement(move: Move): Movement {
        return Movement(Position(move.from.column, move.from.row), Position(move.to.column, move.to.row))
    }

}