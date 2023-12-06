package edu.austral.dissis.chess.common.rules

import edu.austral.dissis.chess.common.Adapter
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.StateEvaluatorResult
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult

interface Rules {
    fun init(): GameState

    fun applyMove(move: Movement): StateEvaluatorResult

    fun getGameState() : GameState
}