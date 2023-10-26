package mychess

import mychess.board.Position

class Adapter {
    private fun positionAdapter(position: Position) : edu.austral.dissis.chess.gui.Position{
        return edu.austral.dissis.chess.gui.Position(position.row , position.column)
    }

}