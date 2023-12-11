package edu.austral.dissis.chess.checkers.state

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.StateEvaluator
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.rules.Game

class CheckersStateEvaluator : StateEvaluator {
    override fun validate(game: Game): StateEvaluatorResult {
        val gameState = game.getGameState()
        when (val currentColor = gameState.turnManager.getCurrentPlayer()) {
            Color.WHITE -> {
                if (colorHasNoPiecesLeft(gameState, currentColor)) {
                    return WinStateResult(Color.WHITE)
                }
            }
            Color.BLACK -> {
                if (colorHasNoPiecesLeft(gameState, currentColor)) {
                    return WinStateResult(Color.BLACK)
                }
            }
        }
        return InProgressStateResult(game)
    }

    private fun colorHasNoPiecesLeft(gameState: GameState, color: Color): Boolean {
        val board = gameState.board
        return board.getPositionMap().entries.none() { it.value.pieceColor == gameState.getNextTurn() }
    }

}