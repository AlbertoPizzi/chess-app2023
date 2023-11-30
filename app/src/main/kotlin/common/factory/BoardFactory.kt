package common.factory

import common.board.Board
import common.board.BoardType
import common.board.Position
import common.board.RegularBoard
import common.piece.Color
import common.piece.Piece
import javafx.geometry.Pos

class BoardFactory {
    companion object {
        fun createNewBoard(boardType: BoardType): Board {
            if (boardType == BoardType.CHESS) {
                return createNewRegularBoard()
            } else if (boardType == BoardType.JEDI_KNIGHTS) {
                return createNewJediKnightBoard()
            }
            else if (boardType == BoardType.CHECKERS){
                return createNewCheckersBoard()
            }
            return createNewRegularBoard()
        }

        fun updateBoard(boardMap: Map<Position, Piece>, board: Board): Board {
            if (board.getBoardType() == BoardType.CHESS) {
                return RegularBoard(BoardType.CHESS, 8, 8, boardMap, board.getPositions())
            }
            if (board.getBoardType() == BoardType.JEDI_KNIGHTS) {
                return RegularBoard(BoardType.JEDI_KNIGHTS, 8, 8, boardMap, board.getPositions())
            }
            if(board.getBoardType() == BoardType.CHECKERS){
                return RegularBoard(BoardType.CHECKERS , 8 , 8 , boardMap , board.getPositions())
            }
            return board
        }
        private fun createNewCheckersBoard(): Board{
            val filledBoard: Map<Position, Piece> = fillWithWhiteCheckers() + fillWithBlackCheckers()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.CHECKERS , 8 , 8 , filledBoard , positionList)
        }

        private fun createNewJediKnightBoard(): Board {
            val filledBoard: Map<Position, Piece> =
                fillWithWhitePawns() + fillWithWhiteKing() +
                        fillWithWhiteRook() + fillWithWhiteBishop() +
                        fillWithWhiteQueen() + fillWithBlackQueen() +
                        fillWithWhiteKnight() + fillWithSM()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.JEDI_KNIGHTS, 8, 8, filledBoard, positionList)
        }


        private fun createNewRegularBoard(): Board {
            val filledBoard: Map<Position, Piece> =
                fillWithWhitePawns() + fillWithBlackPawns() + fillWithWhiteKing() +
                        fillWithWhiteRook() + fillWithBlackRook() + fillWithWhiteBishop() +
                        fillWithWhiteQueen() + fillWithBlackQueen() + fillWithWhiteKnight() +
                        fillWithBlackKnight() + fillWithBlackKing() + fillWithBlackBishop()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.CHESS, 8, 8, filledBoard, positionList)
        }

        private fun fillWithWhiteKing(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 1) to PieceFactory.buildKing("KW", Color.WHITE))
            )
            return mapWithKings
        }

        private fun fillWithBlackKing(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 8) to PieceFactory.buildKing("KB", Color.BLACK))
            )
            return mapWithKings
        }

        private fun fillWithWhitePawns(): Map<Position, Piece> {
            return mapOf(
                //white pawns
                (Position(1, 2) to PieceFactory.buildPawn("PW1", Color.WHITE)),
                (Position(2, 2) to PieceFactory.buildPawn("PW2", Color.WHITE)),
                (Position(3, 2) to PieceFactory.buildPawn("PW3", Color.WHITE)),
                (Position(4, 2) to PieceFactory.buildPawn("PW4", Color.WHITE)),
                (Position(5, 2) to PieceFactory.buildPawn("PW5", Color.WHITE)),
                (Position(6, 2) to PieceFactory.buildPawn("PW6", Color.WHITE)),
                (Position(7, 2) to PieceFactory.buildPawn("PW7", Color.WHITE)),
                (Position(8, 2) to PieceFactory.buildPawn("PW8", Color.WHITE))
            )
        }

        private fun fillWithBlackPawns(): Map<Position, Piece> {
            return mapOf(
                (Position(1, 7) to PieceFactory.buildPawn("PB1", Color.BLACK)),
                (Position(2, 7) to PieceFactory.buildPawn("PB2", Color.BLACK)),
                (Position(3, 7) to PieceFactory.buildPawn("PB3", Color.BLACK)),
                (Position(4, 7) to PieceFactory.buildPawn("PB4", Color.BLACK)),
                (Position(5, 7) to PieceFactory.buildPawn("PB5", Color.BLACK)),
                (Position(6, 7) to PieceFactory.buildPawn("PB6", Color.BLACK)),
                (Position(7, 7) to PieceFactory.buildPawn("PB7", Color.BLACK)),
                (Position(8, 7) to PieceFactory.buildPawn("PB8", Color.BLACK)),
            )
        }

        private fun fillWithWhiteBishop(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(3, 1) to PieceFactory.buildBishop("BW1", Color.WHITE)),
                (Position(6, 1) to PieceFactory.buildBishop("BW2", Color.WHITE))
            )
        }

        private fun fillWithBlackBishop(): Map<Position, Piece> {
            return mapOf(
                (Position(3, 8) to PieceFactory.buildBishop("BB1", Color.BLACK)),
                (Position(6, 8) to PieceFactory.buildBishop("BB2", Color.BLACK))
            )
        }

        private fun fillWithWhiteRook(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(1, 1) to PieceFactory.buildRook("RW1", Color.WHITE)),
                (Position(8, 1) to PieceFactory.buildRook("RW2", Color.WHITE))
            )
        }

        private fun fillWithBlackRook(): Map<Position, Piece> {
            return mapOf(
                (Position(1, 8) to PieceFactory.buildRook("RB1", Color.BLACK)),
                (Position(8, 8) to PieceFactory.buildRook("RB2", Color.BLACK))
            )
        }

        private fun fillWithWhiteQueen(): Map<Position, Piece> {
            return mapOf(
                (Position(4, 1) to PieceFactory.buildQueen("QW", Color.WHITE))
            )
        }

        private fun fillWithBlackQueen(): Map<Position, Piece> {
            return mapOf(
                (Position(4, 8) to PieceFactory.buildQueen("QB", Color.BLACK))
            )
        }

        private fun fillWithWhiteKnight(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(2, 1) to PieceFactory.buildKnight("KNW1", Color.WHITE)),
                (Position(7, 1) to PieceFactory.buildKnight("KNW2", Color.WHITE))
            )
        }

        private fun fillWithBlackKnight(): Map<Position, Piece> {
            return mapOf(
                (Position(2, 8) to PieceFactory.buildKnight("KNB1", Color.BLACK)),
                (Position(7, 8) to PieceFactory.buildKnight("KNB2", Color.BLACK))
            )
        }

        private fun fillWithSM(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 8) to PieceFactory.buildSithMaster("SM", Color.BLACK))
            )
            return mapWithKings
        }
        private fun fillWithWhiteCheckers(): Map<Position , Piece> {
            val mapWithWhiteCheckers : Map<Position , Piece> = mapOf(
                (Position(2 , 8) to PieceFactory.buildChecker("WC1" , Color.BLACK)),
                (Position(4 , 8) to PieceFactory.buildChecker("WC2" , Color.BLACK)),
                (Position(6 , 8) to PieceFactory.buildChecker("WC3" , Color.BLACK)),
                (Position(8 , 8) to PieceFactory.buildChecker("WC4" , Color.BLACK)),
                (Position(1 , 7) to PieceFactory.buildChecker("WC5" , Color.BLACK)),
                (Position(3 , 7) to PieceFactory.buildChecker("WC6" , Color.BLACK)),
                (Position(5 , 7) to PieceFactory.buildChecker("WC7" , Color.BLACK)),
                (Position(7 , 7) to PieceFactory.buildChecker("WC8" , Color.BLACK)),
                (Position(2 , 6) to PieceFactory.buildChecker("WC9" , Color.BLACK)),
                (Position(4 , 6) to PieceFactory.buildChecker("WC10" , Color.BLACK)),
                (Position(6 , 6) to PieceFactory.buildChecker("WC11" , Color.BLACK)),
                (Position(8 , 6) to PieceFactory.buildChecker("WC12" , Color.BLACK)),
            )
            return mapWithWhiteCheckers
        }
        private fun fillWithBlackCheckers(): Map<Position , Piece> {
            val mapWithBlackCheckers : Map<Position , Piece> = mapOf(
                (Position(1 , 1) to PieceFactory.buildChecker("BC1" , Color.WHITE)),
                (Position(3 , 1) to PieceFactory.buildChecker("BC2" , Color.WHITE)),
                (Position(5 , 1) to PieceFactory.buildChecker("BC3" , Color.WHITE)),
                (Position(7 , 1) to PieceFactory.buildChecker("BC4" , Color.WHITE)),
                (Position(2 , 2) to PieceFactory.buildChecker("BC5" , Color.WHITE)),
                (Position(4 , 2) to PieceFactory.buildChecker("BC6" , Color.WHITE)),
                (Position(6 , 2) to PieceFactory.buildChecker("BC7" , Color.WHITE)),
                (Position(8 , 2) to PieceFactory.buildChecker("BC8" , Color.WHITE)),
                (Position(1 , 3) to PieceFactory.buildChecker("BC9" , Color.WHITE)),
                (Position(3 , 3) to PieceFactory.buildChecker("BC10" , Color.WHITE)),
                (Position(5 , 3) to PieceFactory.buildChecker("BC11" , Color.WHITE)),
                (Position(7 , 3) to PieceFactory.buildChecker("BC12" , Color.WHITE)),
            )
            return mapWithBlackCheckers
        }
    }
}
