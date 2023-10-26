package mychess.game

import edu.austral.dissis.chess.gui.GameEngine
import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult
import mychess.movement.Movement

class ApeEngine : GameEngine {
    private val game  = Game()
    override fun applyMove(move: Move): MoveResult {
        val movement : Movement = game.getAdapter().translateMovement(move)
        return game.getAdapter().adaptMoveResult(game.applyMove(movement , game.getAdapter().getLastState()))
    }

    override fun init(): InitialState {
        return game.getAdapter().adaptGameStateToInitialState(game.init())
    }
}