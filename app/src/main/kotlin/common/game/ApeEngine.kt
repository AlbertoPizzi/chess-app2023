package common.game

import checkers.CheckersRules
import edu.austral.dissis.chess.gui.*
import common.Rules
import mychess.ChessRules

class ApeEngine(rules : Rules) : GameEngine {
    private val game = rules

    companion object{
        fun chessEngine() : ApeEngine{
            return ApeEngine(ChessRules())
        }
        fun checkersEngine() : ApeEngine{
            return ApeEngine(CheckersRules())
        }

    }
    override fun init(): InitialState {
        return game.init()
    }



    override fun applyMove(move: Move): MoveResult {
       return game.applyMove(move)
    }


}