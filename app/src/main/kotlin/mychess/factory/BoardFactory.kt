package mychess.factory

import javafx.geometry.Pos
import mychess.board.Board
import mychess.board.BoardType
import mychess.board.Position
import mychess.board.RegularBoard
import mychess.piece.Color
import mychess.piece.Piece
import mychess.result.FailureResult

class BoardFactory {
    companion object {
        fun createNewBoard(boardType: BoardType): Board {
            if (boardType == BoardType.REGULAR) {
                return createNewRegularBoard()
            }
            return createNewRegularBoard()
        }
        fun updateBoard(boardMap: Map<Position , Piece>, board : Board ): Board{
            if(board.getBoardType() == BoardType.REGULAR){
                return RegularBoard(BoardType.REGULAR , 8 , 8 , boardMap , board.getPositions())
            }
            return board
        }

        fun createNewRegularBoard(): RegularBoard {
            val filledBoard : Map<Position, Piece> =  fillWithPawns() + fillWithKings() + fillWithRook() + fillWithBishop() + fillWithQueen() + fillWithKnight()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.REGULAR , 8 , 8 , filledBoard , positionList)
        }
        fun fillWithKings() : Map<Position, Piece>{
            val mapWithKings : Map<Position, Piece> = mapOf(
                (Position(5 , 1) to  PieceFactory.buildKing(Color.WHITE)),
                (Position(5 , 8 ) to PieceFactory.buildKing(Color.BLACK))
            )
            return mapWithKings
        }
        fun fillWithPawns(): Map<Position, Piece> {

            return mapOf(
                //white pawns
                (Position(1, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(2, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(3, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(4, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(5, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(6, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(7, 2) to PieceFactory.buildPawn(Color.WHITE)),
                (Position(8, 2) to PieceFactory.buildPawn(Color.WHITE)),
                //black pawns
                (Position(1, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(2, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(3, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(4, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(5, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(6, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(7, 7) to PieceFactory.buildPawn(Color.BLACK)),
                (Position(8, 7) to PieceFactory.buildPawn(Color.BLACK)),
            )
        }
        fun fillWithBishop(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(3, 1) to PieceFactory.buildBishop(Color.WHITE)),
                (Position(6, 1) to PieceFactory.buildBishop(Color.WHITE)),
                //black
                (Position(3, 8) to PieceFactory.buildBishop(Color.BLACK)),
                (Position(6, 8) to PieceFactory.buildBishop(Color.BLACK))
            )
        }
        fun fillWithRook() : Map<Position , Piece>{
            return mapOf(
                //white
                (Position(1 , 1 ) to PieceFactory.buildRook(Color.WHITE)),
                (Position(8 , 1 ) to PieceFactory.buildRook(Color.WHITE)),
                //black
                (Position(1 , 8 ) to PieceFactory.buildRook(Color.BLACK)),
                (Position(8 , 8 ) to PieceFactory.buildRook(Color.BLACK))
            )
        }
        fun fillWithQueen() : Map<Position , Piece>{
            return mapOf(
                (Position(4, 1) to PieceFactory.buildQueen(Color.WHITE)),
                (Position(4, 8) to PieceFactory.buildQueen(Color.BLACK))
            )
        }
        fun fillWithKnight() : Map<Position , Piece>{
            return mapOf(
                //white
                (Position(2, 1 ) to PieceFactory.buildKnight(Color.WHITE)),
                (Position(7, 1 ) to PieceFactory.buildKnight(Color.WHITE)),
                //black
                (Position(2, 8 ) to PieceFactory.buildKnight(Color.BLACK)),
                (Position(7, 8 ) to PieceFactory.buildKnight(Color.BLACK))
            )
        }

    }
}
