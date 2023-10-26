package mychess

import edu.austral.dissis.chess.gui.*
import mychess.board.Board
import mychess.board.Position
import mychess.game.GameState
import mychess.movement.Movement
import mychess.movement.PieceMover
import mychess.piece.Color
import mychess.piece.Piece
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class Adapter {
    private fun positionAdapter(position: Position) : edu.austral.dissis.chess.gui.Position{
        return edu.austral.dissis.chess.gui.Position(position.row , position.column)
    }
    private fun colorAdapter(color: Color) : PlayerColor{
        return if(color == Color.WHITE) PlayerColor.WHITE
        else PlayerColor.BLACK
    }
    private fun chessPieceAdapter(board: Board ,piece : Piece) : ChessPiece{
        return ChessPiece(piece.getId(), colorAdapter(piece.getPieceColor()) ,positionAdapter(board.getPositionByPiece(piece))  , piece.getId())
    }
    private fun moveAdapter(movement : Movement) : Move{
        return Move(positionAdapter(movement.initpos) , positionAdapter(movement.finalpos))
    }
    private fun piecesAdapter(board: Board , pieceList : List<Piece>): List<ChessPiece>{
        val chessPieceList : MutableList<ChessPiece> = mutableListOf()
        pieceList.forEach{
            piece : Piece -> chessPieceList.add(chessPieceAdapter(board , piece))
        }
        return chessPieceList
    }
    private fun adaptBoardSize(row : Int , column : Int) : BoardSize{
        return BoardSize(row , column)
    }
    private var states : MutableList<GameState> = mutableListOf()

    fun saveHistory(gameState: GameState){
        states.add(gameState)
    }
    fun getLastState() : GameState{
        return states.last()
    }
    fun adaptGameStateToInitialState(gameState: GameState) : InitialState {
        val board : Board = gameState.getBoardHistory().last()
        val boardSize = adaptBoardSize(board.getRowSize(), board.getColSize())
        val chessPieces = piecesAdapter(board, board.getPositionMap().values.toList())
        val playerColor = colorAdapter(gameState.getTurnManager().getCurrentPlayer())
        return InitialState(boardSize, chessPieces, playerColor)
    }
    fun adaptMoveResult(result : ResultValidator) : MoveResult{
        val gameState : GameState = states.last()
        val board : Board = gameState.getBoardHistory().last()
        val chessPieces = piecesAdapter(board, board.getPositionMap().values.toList())
        val playerColor = colorAdapter(gameState.getTurnManager().getCurrentPlayer())
        return when(result){
            is FailureResult -> InvalidMove(result.message )
            is SuccessfulResult -> NewGameState(chessPieces, playerColor)
            is mychess.result.GameOver -> GameOver(if (playerColor == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE)
        }
    }

    fun translateMovement(move : Move) : Movement{

        return Movement(Position(move.from.column , move.from.row) , Position(move.to.column , move.to.row))
    }
}