package mychess.factory

import javafx.geometry.Pos
import mychess.board.Board
import mychess.board.BoardType
import mychess.board.Position
import mychess.board.RegularBoard
import mychess.piece.Color
import mychess.piece.Piece

class BoardFactory {
    companion object {
        fun createNewBoard(boardMap: Map<Position, Piece>, board: Board): Board {
            if (board.getBoardType() == BoardType.REGULAR) {
                return createNewRegularBoard()
            }
            return board
        }

        fun createNewRegularBoard(): RegularBoard {
            val filledBoard = fillWithPawns() + fillWithKings() + fillWithRook() + fillWithBishop() + fillWithQueen() + fillWithKnight()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.REGULAR , 8 , 8 , filledBoard , positionList)
        }
        fun fillWithKings() : Map<Position, Piece>{
            val mapWithKings : Map<Position, Piece>
            mapWithKings = mutableMapOf(
                (Position(4 , 0) to  PieceFactory.buildKing(Color.WHITE)),
                (Position(4 , 7 ) to PieceFactory.buildKing(Color.BLACK))
            )
            return mapWithKings
        }
        fun fillWithPawns(): Map<Position, Piece> {

            return mutableMapOf(
                //white pawns
                (Position(0, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(1, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(2, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(3, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(4, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(5, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(6, 1) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(6, 1) to PieceFactory.buildPawn(Color.WHITE)),
                //black pawns
                (Position(0, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(1, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(2, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(3, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(4, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(5, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(6, 6) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(7, 6) to PieceFactory.buildPawn(Color.BLACK)),
            )
        }
        fun fillWithBishop(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(2, 0) to PieceFactory.buildBishop(Color.WHITE)),
                (Position(5, 0) to PieceFactory.buildBishop(Color.WHITE)),
                //black
                (Position(2, 7) to PieceFactory.buildBishop(Color.BLACK)),
                (Position(5, 7) to PieceFactory.buildBishop(Color.BLACK))
            )
        }
        fun fillWithRook() : Map<Position , Piece>{
            return mapOf(
                //white
                (Position(0 , 0 ) to PieceFactory.buildRook(Color.WHITE)),
                (Position(7 , 0 ) to PieceFactory.buildRook(Color.WHITE)),
                //black
                (Position(0 , 7 ) to PieceFactory.buildRook(Color.WHITE)),
                (Position(7 , 7 ) to PieceFactory.buildRook(Color.WHITE))
            )
        }
        fun fillWithQueen() : Map<Position , Piece>{
            return mapOf(
                (Position(3, 0) to PieceFactory.buildQueen(Color.WHITE)),
                (Position(3, 7) to PieceFactory.buildQueen(Color.BLACK)),
            )
        }
        fun fillWithKnight() : Map<Position , Piece>{
            return mapOf(
                //white
                (Position(1, 0 ) to PieceFactory.buildKnight(Color.WHITE)),
                (Position(6, 0 ) to PieceFactory.buildKnight(Color.WHITE)),
                //black
                (Position(1, 7 ) to PieceFactory.buildKnight(Color.BLACK)),
                (Position(6, 7 ) to PieceFactory.buildKnight(Color.BLACK))
            )
        }

    }
}
