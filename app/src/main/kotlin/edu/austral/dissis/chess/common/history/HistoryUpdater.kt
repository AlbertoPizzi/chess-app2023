package edu.austral.dissis.chess.common.history

import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.rules.Game

class HistoryUpdater {
    fun updateHistory(game : Game): Game {
        val currentGS = game.getGameState()
        val auxBoard = currentGS.board
        val newHistory = currentGS.history.copy(boardHistory = currentGS.history.boardHistory + auxBoard)
        return game.copy(state = currentGS.copy(history = newHistory))
    }
}