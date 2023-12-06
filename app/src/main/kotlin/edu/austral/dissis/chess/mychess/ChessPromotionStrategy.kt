package edu.austral.dissis.chess.mychess

import edu.austral.dissis.chess.common.PromotionStrategy
import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.PieceType

class ChessPromotionStrategy : PromotionStrategy {
    override fun promote(gameState: GameState ): GameState { //Busca si hay algun Peon en los limites del board
        val board = gameState.board
        val pawns = board.getPositionMap().filter { it.value.pieceColor == gameState.turnManager.getCurrentPlayer() && it.value.type == PieceType.PAWN }
        val toRow = if(gameState.turnManager.getCurrentPlayer() == Color.WHITE) board.getRowSize()  else 1
        for ((position, piece) in pawns){
            val pawnPosition = position
            if (comparePositionToBoardLimit(pawnPosition, toRow)){
                val auxPieceFactory = ChessPieceFactory
                val newQueen = auxPieceFactory.buildQueen(piece.id, gameState.turnManager.getCurrentPlayer())
                var newMutableMap = gameState.getPositionMap().toMutableMap()
                newMutableMap.replace(pawnPosition, newQueen)
                return gameState.copy(board = BoardFactory.updateBoard(newMutableMap.toMap(), board))
            }
        }
        return gameState

    }
    private fun comparePositionToBoardLimit(position: Position, row : Int): Boolean {
        return position.row == row
    }
    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}