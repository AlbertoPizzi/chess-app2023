package mychess.state

import common.game.GameState
import common.gamestates.InProgressStateResult
import common.gamestates.StateEvaluator
import common.gamestates.StateEvaluatorResult
import common.gamestates.WinStateResult
import common.piece.Color

class ChessStateEvaluator: StateEvaluator {
    override fun validate(gameState: GameState): StateEvaluatorResult {
        if(isCheckMate(gameState)) {
            when(gameState.getTurnManager().getCurrentPlayer()) {
                Color.WHITE -> return WinStateResult(Color.BLACK)
                Color.BLACK -> return WinStateResult(Color.WHITE)
            }
        }
        else return InProgressStateResult()
    }

    fun isCheckMate(gameState: GameState): Boolean{
        val turnManager = gameState.getTurnManager()
        val newGameState = gameState.copy(turnManager.nextTurn())
        if(newGameState.isKingThreaten(turnManager.getCurrentPlayer())){
            val pieceList = newGameState.getBoardHistory().last().getPositionMap().entries.filter { it.value.pieceColor == newGameState.getTurnManager().getCurrentPlayer() }
            for (piece in pieceList) {
                if (!newGameState.chessPieceHasAnyValidMovement(piece.value)) {
                    return true
                }
            }
            return false
        }
        return false
    }


}