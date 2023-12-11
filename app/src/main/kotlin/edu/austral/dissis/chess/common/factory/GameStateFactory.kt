package edu.austral.dissis.chess.common.factory

import edu.austral.dissis.chess.common.board.BoardType
import edu.austral.dissis.chess.common.game.turnmanagement.ClassicTurn
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.history.History
import edu.austral.dissis.chess.common.piece.Color

class GameStateFactory {
    fun chessGameStateBuilder(): GameState {
        return GameState(
            ClassicTurn(Color.WHITE),
            History(listOf()),
            BoardFactory.createNewBoard(BoardType.CHESS),

        )
    }

    fun checkersStateBuilder(): GameState {
        return GameState(
            ClassicTurn(Color.WHITE),
            History(listOf()),
            BoardFactory.createNewBoard(BoardType.CHECKERS),

        )
    }

    fun jediKnightStateBuilder(): GameState {
        return GameState(
            ClassicTurn(Color.WHITE),
            History(listOf()),
            BoardFactory.createNewBoard(BoardType.JEDI_KNIGHTS),

        )
    }

    fun capaBlancaStateBuilder(): GameState {
        return GameState(
            ClassicTurn(Color.WHITE),
            History(listOf()),
            BoardFactory.createNewBoard(BoardType.CAPABLANCA),

        )
    }
    fun emptyBoardBuilder(): GameState {
        return GameState(
            ClassicTurn(Color.WHITE),
            History(listOf()),
            BoardFactory.createNewRegularBoard(),

        )
    }
}