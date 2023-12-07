package edu.austral.dissis.chess.common.game

import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.turnmanagement.TurnManager
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.history.History
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece

data class GameState(
    val turnManager: TurnManager,
    val history: History,
    val board: Board,
    val state: StateEvaluatorResult
) {

    fun getCurrentPlayer(): Color {
        return turnManager.getCurrentPlayer()
    }

    fun getPositionMap(): Map<Position, Piece> {
        return board.getPositionMap()
    }

    fun getPiece(position: Position): Piece {
        return board.getPositionMap().get(position)!!
    }

    fun getNextTurn(): Color {
        return turnManager.nextTurn().getCurrentPlayer()
    }

    fun getPositionByPieceID(id: String): Position {
        return board.getPositionMap().entries.find { it.value.id == id }!!.key
    }


}