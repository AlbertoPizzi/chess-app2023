package checkers.state

import common.game.GameState
import common.gamestates.InProgressStateResult
import common.gamestates.StateEvaluator
import common.gamestates.StateEvaluatorResult
import common.gamestates.WinStateResult
import common.piece.Color

class CheckersStateEvaluator : StateEvaluator {
    override fun validate(gameState: GameState): StateEvaluatorResult {

        when(val currentColor = gameState.getTurnManager().getCurrentPlayer()){
            Color.WHITE -> {
                if(colorHasNoPiecesLeft(gameState , currentColor)){
                     return WinStateResult(Color.BLACK)
                }
            }
            Color.BLACK -> {
                if(colorHasNoPiecesLeft(gameState, currentColor)){
                    return  WinStateResult(Color.WHITE)
                }
            }
        }
        return InProgressStateResult()
    }
    private fun colorHasNoPiecesLeft(gameState: GameState , color: Color) : Boolean{
        val board = gameState.getBoardHistory().last()
        return board.getPositionMap().entries.none(){ it.value.pieceColor == gameState.getTurnManager().getCurrentPlayer() }
    }

}