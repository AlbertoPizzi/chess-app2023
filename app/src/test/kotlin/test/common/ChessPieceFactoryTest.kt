package test.common

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.BoardFactory
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.factory.GameStateFactory
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece

class ChessPieceFactoryTest {

    fun generatePieceInTheMiddle(piece : Piece) : GameState{
        var gamestate = GameStateFactory().emptyBoardBuilder()
        val pieceMap = mapOf((Position(5, 5) to piece))
        return gamestate.copy(board = BoardFactory.updateBoard(pieceMap , gamestate.board))
    }
    fun generatePieceInTheMiddleWithEnemy(piece : Piece) : GameState{
        val enemyColour = when(piece.pieceColor) {
            Color.WHITE -> Color.BLACK
            Color.BLACK -> Color.WHITE
        }
        var gamestate = GameStateFactory().emptyBoardBuilder()
        val pieceMap = mapOf(
            (Position(5, 5) to piece),
            (Position(4, 4) to ChessPieceFactory.buildPawn("EP1", enemyColour)),
            (Position(4, 6) to ChessPieceFactory.buildPawn("EP2", enemyColour)),
            (Position(6, 4) to ChessPieceFactory.buildPawn("EP3", enemyColour)),
            (Position(6, 6) to ChessPieceFactory.buildPawn("EP4", enemyColour)),
            )

        return gamestate.copy(board = BoardFactory.updateBoard(pieceMap , gamestate.board))
    }
    fun generateWithAlly(piece: Piece) : GameState{
        val allyColour = piece.pieceColor
        var gamestate = GameStateFactory().emptyBoardBuilder()
        val pieceMap = mapOf(
            (Position(5, 5) to piece),
            (Position(4, 4) to ChessPieceFactory.buildPawn("EP1", allyColour)),
            (Position(4, 6) to ChessPieceFactory.buildPawn("EP2", allyColour)),
            (Position(6, 4) to ChessPieceFactory.buildPawn("EP3", allyColour)),
            (Position(6, 6) to ChessPieceFactory.buildPawn("EP4", allyColour)),
            )
        return gamestate.copy(board = BoardFactory.updateBoard(pieceMap , gamestate.board))
    }
    fun generateWithPawnInInitialPosition(piece: Piece): GameState{
        var gamestate = GameStateFactory().emptyBoardBuilder()
        val pieceMap = mapOf((Position(5, 2) to piece))
        return gamestate.copy(board = BoardFactory.updateBoard(pieceMap , gamestate.board))
    }
}