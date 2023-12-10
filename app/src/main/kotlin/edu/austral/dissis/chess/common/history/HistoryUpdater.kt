package edu.austral.dissis.chess.common.history

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.rules.Game

class HistoryUpdater {
    fun updateHistory(game : Game): Game {
        val gameState = game.getGameState()
        val auxBoard = gameState.board
        val newHistory = gameState.history.copy(boardHistory = gameState.history.boardHistory + auxBoard)
        return game.copy(state= gameState.copy(history = newHistory))
    }
}