package common

import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult

interface Rules {
    fun init(): InitialState
    fun getAdapter(): Adapter
    fun applyMove(move: Move): MoveResult
}