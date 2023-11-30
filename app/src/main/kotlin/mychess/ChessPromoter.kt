package mychess

import common.Promoter
import common.board.Board
import common.factory.BoardFactory
import common.factory.PieceFactory
import common.game.GameState
import common.movementvalidators.Movement
import common.piece.Color
import common.piece.PieceType

class ChessPromoter : Promoter {
    override fun promote(gameState: GameState , movement: Movement): GameState {
        val board = gameState.getBoardHistory().last()
        val pieceToPromote = board.getPieceByPosition(movement.initpos)!!
        if(isPromotionable(board , movement)){
            val newQueen = PieceFactory.buildQueen("QW2" , pieceToPromote.pieceColor)
            val positionMapCopy = board.getPositionMap().toMutableMap()
            positionMapCopy.replace(movement.initpos , pieceToPromote , newQueen)
            positionMapCopy.toMap()
            BoardFactory.updateBoard(positionMapCopy , board)
            val newBoardHistory = createHistoryFromBoard(board)
            return gameState.copy(boardHistory = newBoardHistory)
        }
        return gameState

    }
    private fun isPromotionable(board: Board, movement: Movement): Boolean {
        val pieceToPromote = board.getPieceByPosition(movement.initpos)!!
        if (pieceToPromote.type == PieceType.PAWN) {
            return when (pieceToPromote.pieceColor) {
                Color.WHITE -> movement.finalpos.row == board.getRowSize()
                Color.BLACK -> movement.finalpos.row == 1
            }
        }
        return false
    }
    private fun createHistoryFromBoard(board: Board): List<Board> {
        val boardHistory: MutableList<Board> = mutableListOf()
        boardHistory.add(board)
        return boardHistory
    }
}