package edu.austral.dissis.chess.common.history

import edu.austral.dissis.chess.common.game.GameState

class HistoryUpdater {
    fun updateHistory(gameState: GameState): GameState {
        val auxBoard = gameState.board
        val newHistory = gameState.history.copy(boardHistory = gameState.history.boardHistory + auxBoard)
        return gameState.copy(history = newHistory)
    }
}