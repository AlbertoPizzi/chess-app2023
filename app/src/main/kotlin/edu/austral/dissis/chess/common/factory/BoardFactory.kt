package edu.austral.dissis.chess.common.factory

import edu.austral.dissis.chess.common.board.Board
import edu.austral.dissis.chess.common.board.BoardType
import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.board.RegularBoard
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.piece.Piece

class BoardFactory {
    companion object {
        fun createNewBoard(boardType: BoardType): Board {
            if (boardType == BoardType.CHESS) {
                return createNewChessBoard()
            } else if (boardType == BoardType.JEDI_KNIGHTS) {
                return createNewJediKnightBoard()
            } else if (boardType == BoardType.CHECKERS) {
                return createNewCheckersBoard()
            } else if (boardType == BoardType.CAPABLANCA) {
                return createNewCapaBlanca()
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
            if (board.getBoardType() == BoardType.CHECKERS) {
                return RegularBoard(BoardType.CHECKERS, 8, 8, boardMap, board.getPositions())
            }
            if (board.getBoardType() == BoardType.CAPABLANCA) {
                return RegularBoard(BoardType.CAPABLANCA, 8, 10, boardMap, board.getPositions())
            }
            if(board.getBoardType() == BoardType.EMPTY_BOARD){
                return RegularBoard(BoardType.EMPTY_BOARD, 8, 8, boardMap, board.getPositions())
            }
            return board
        }

        fun createBoardFromReference(board: Board, movement: Movement): Board {
            val pieceToMove = board.getPositionMap()[movement.initpos]!!

            val updatedPieceMap = board.getPositionMap()
                .filterKeys { it != movement.initpos }
                .plus(movement.finalpos to pieceToMove)

            return updateBoard(updatedPieceMap, board)
        }

        private fun createNewCheckersBoard(): Board {
            val filledBoard: Map<Position, Piece> = fillWithWhiteCheckers() + fillWithBlackCheckers()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.CHECKERS, 8, 8, filledBoard, positionList)
        }

        private fun createNewCapaBlanca(): Board {
            val filledBoard: Map<Position, Piece> = fillWithCapaBlanca()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.CAPABLANCA, 8, 10, filledBoard, positionList)
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


        private fun createNewChessBoard(): Board {
            val filledBoard: Map<Position, Piece> =
                fillWithWhitePawns() + fillWithBlackPawns() + fillWithWhiteKing() +
                        fillWithWhiteRook() + fillWithBlackRook() + fillWithWhiteBishop() +
                        fillWithWhiteQueen() + fillWithBlackQueen() + fillWithWhiteKnight() +
                        fillWithBlackKnight() + fillWithBlackKing() + fillWithBlackBishop()
            val positionList = filledBoard.keys.toList()
            return RegularBoard(BoardType.CHESS, 8, 8, filledBoard, positionList)
        }

         fun createNewRegularBoard(): Board {
            val newBoard: Map<Position, Piece> = emptyMap<Position, Piece>()
            val positionList = newBoard.keys.toList()
            return RegularBoard(BoardType.EMPTY_BOARD, 8, 8, newBoard, positionList)
        }

        private fun fillWithWhiteKing(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 1) to ChessPieceFactory.buildKing("KW", Color.WHITE))
            )
            return mapWithKings
        }

        private fun fillWithBlackKing(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 8) to ChessPieceFactory.buildKing("KB", Color.BLACK))
            )
            return mapWithKings
        }

        private fun fillWithWhitePawns(): Map<Position, Piece> {
            return mapOf(
                //white pawns
                (Position(1, 2) to ChessPieceFactory.buildPawn("PW1", Color.WHITE)),
                (Position(2, 2) to ChessPieceFactory.buildPawn("PW2", Color.WHITE)),
                (Position(3, 2) to ChessPieceFactory.buildPawn("PW3", Color.WHITE)),
                (Position(4, 2) to ChessPieceFactory.buildPawn("PW4", Color.WHITE)),
                (Position(5, 2) to ChessPieceFactory.buildPawn("PW5", Color.WHITE)),
                (Position(6, 2) to ChessPieceFactory.buildPawn("PW6", Color.WHITE)),
                (Position(7, 2) to ChessPieceFactory.buildPawn("PW7", Color.WHITE)),
                (Position(8, 2) to ChessPieceFactory.buildPawn("PW8", Color.WHITE))
            )
        }

        private fun fillWithBlackPawns(): Map<Position, Piece> {
            return mapOf(
                (Position(1, 7) to ChessPieceFactory.buildPawn("PB1", Color.BLACK)),
                (Position(2, 7) to ChessPieceFactory.buildPawn("PB2", Color.BLACK)),
                (Position(3, 7) to ChessPieceFactory.buildPawn("PB3", Color.BLACK)),
                (Position(4, 7) to ChessPieceFactory.buildPawn("PB4", Color.BLACK)),
                (Position(5, 7) to ChessPieceFactory.buildPawn("PB5", Color.BLACK)),
                (Position(6, 7) to ChessPieceFactory.buildPawn("PB6", Color.BLACK)),
                (Position(7, 7) to ChessPieceFactory.buildPawn("PB7", Color.BLACK)),
                (Position(8, 7) to ChessPieceFactory.buildPawn("PB8", Color.BLACK)),
            )
        }

        private fun fillWithWhiteBishop(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(3, 1) to ChessPieceFactory.buildBishop("BW1", Color.WHITE)),
                (Position(6, 1) to ChessPieceFactory.buildBishop("BW2", Color.WHITE))
            )
        }

        private fun fillWithBlackBishop(): Map<Position, Piece> {
            return mapOf(
                (Position(3, 8) to ChessPieceFactory.buildBishop("BB1", Color.BLACK)),
                (Position(6, 8) to ChessPieceFactory.buildBishop("BB2", Color.BLACK))
            )
        }

        private fun fillWithWhiteRook(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(1, 1) to ChessPieceFactory.buildRook("RW1", Color.WHITE)),
                (Position(8, 1) to ChessPieceFactory.buildRook("RW2", Color.WHITE))
            )
        }

        private fun fillWithBlackRook(): Map<Position, Piece> {
            return mapOf(
                (Position(1, 8) to ChessPieceFactory.buildRook("RB1", Color.BLACK)),
                (Position(8, 8) to ChessPieceFactory.buildRook("RB2", Color.BLACK))
            )
        }

        private fun fillWithWhiteQueen(): Map<Position, Piece> {
            return mapOf(
                (Position(4, 1) to ChessPieceFactory.buildQueen("QW", Color.WHITE))
            )
        }

        private fun fillWithBlackQueen(): Map<Position, Piece> {
            return mapOf(
                (Position(4, 8) to ChessPieceFactory.buildQueen("QB", Color.BLACK))
            )
        }

        private fun fillWithWhiteKnight(): Map<Position, Piece> {
            return mapOf(
                //white
                (Position(2, 1) to ChessPieceFactory.buildKnight("KNW1", Color.WHITE)),
                (Position(7, 1) to ChessPieceFactory.buildKnight("KNW2", Color.WHITE))
            )
        }

        private fun fillWithBlackKnight(): Map<Position, Piece> {
            return mapOf(
                (Position(2, 8) to ChessPieceFactory.buildKnight("KNB1", Color.BLACK)),
                (Position(7, 8) to ChessPieceFactory.buildKnight("KNB2", Color.BLACK))
            )
        }

        private fun fillWithSM(): Map<Position, Piece> {
            val mapWithKings: Map<Position, Piece> = mapOf(
                (Position(5, 8) to ChessPieceFactory.buildSithMaster("KB", Color.BLACK))
            )
            return mapWithKings
        }

        private fun fillWithBlackCheckers(): Map<Position, Piece> {
            val mapWithWhiteCheckers: Map<Position, Piece> = mapOf(
                (Position(2, 8) to CheckersPieceFactory.buildChecker("BC1", Color.BLACK)),
                (Position(4, 8) to CheckersPieceFactory.buildChecker("BC2", Color.BLACK)),
                (Position(6, 8) to CheckersPieceFactory.buildChecker("BC3", Color.BLACK)),
                (Position(8, 8) to CheckersPieceFactory.buildChecker("BC4", Color.BLACK)),
                (Position(1, 7) to CheckersPieceFactory.buildChecker("BC5", Color.BLACK)),
                (Position(3, 7) to CheckersPieceFactory.buildChecker("BC6", Color.BLACK)),
                (Position(5, 7) to CheckersPieceFactory.buildChecker("BC7", Color.BLACK)),
                (Position(7, 7) to CheckersPieceFactory.buildChecker("BC8", Color.BLACK)),
                (Position(2, 6) to CheckersPieceFactory.buildChecker("BC9", Color.BLACK)),
                (Position(4, 6) to CheckersPieceFactory.buildChecker("BC10", Color.BLACK)),
                (Position(6, 6) to CheckersPieceFactory.buildChecker("BC11", Color.BLACK)),
                (Position(8, 6) to CheckersPieceFactory.buildChecker("BC12", Color.BLACK)),
            )
            return mapWithWhiteCheckers
        }

        private fun fillWithWhiteCheckers(): Map<Position, Piece> {
            val mapWithBlackCheckers: Map<Position, Piece> = mapOf(
                (Position(1, 1) to CheckersPieceFactory.buildChecker("WC1", Color.WHITE)),
                (Position(3, 1) to CheckersPieceFactory.buildChecker("WC2", Color.WHITE)),
                (Position(5, 1) to CheckersPieceFactory.buildChecker("WC3", Color.WHITE)),
                (Position(7, 1) to CheckersPieceFactory.buildChecker("WC4", Color.WHITE)),
                (Position(2, 2) to CheckersPieceFactory.buildChecker("WC5", Color.WHITE)),
                (Position(4, 2) to CheckersPieceFactory.buildChecker("WC6", Color.WHITE)),
                (Position(6, 2) to CheckersPieceFactory.buildChecker("WC7", Color.WHITE)),
                (Position(8, 2) to CheckersPieceFactory.buildChecker("WC8", Color.WHITE)),
                (Position(1, 3) to CheckersPieceFactory.buildChecker("WC9", Color.WHITE)),
                (Position(3, 3) to CheckersPieceFactory.buildChecker("WC10", Color.WHITE)),
                (Position(5, 3) to CheckersPieceFactory.buildChecker("WC11", Color.WHITE)),
                (Position(7, 3) to CheckersPieceFactory.buildChecker("WC12", Color.WHITE)),
            )
            return mapWithBlackCheckers
        }

        private fun fillWithCapaBlanca(): Map<Position, Piece> {
            val pieceMap = mapOf(
                (Position(1, 1) to ChessPieceFactory.buildRook("RW1", Color.WHITE)),
                (Position(2, 1) to ChessPieceFactory.buildKnight("KW1", Color.WHITE)),
                (Position(3, 1) to ChessPieceFactory.buildArchBishop("AW", Color.WHITE)),
                (Position(4, 1) to ChessPieceFactory.buildBishop("BW", Color.WHITE)),
                (Position(5, 1) to ChessPieceFactory.buildQueen("QW", Color.WHITE)),
                (Position(6, 1) to ChessPieceFactory.buildKing("KW", Color.WHITE)),
                (Position(7, 1) to ChessPieceFactory.buildBishop("BW2", Color.WHITE)),
                (Position(8, 1) to ChessPieceFactory.buildChancellor("CW", Color.WHITE)),
                (Position(9, 1) to ChessPieceFactory.buildKnight("KW2", Color.WHITE)),
                (Position(10, 1) to ChessPieceFactory.buildRook("RW2", Color.WHITE)),


                (Position(1, 2) to ChessPieceFactory.buildPawn("PW1", Color.WHITE)),
                (Position(2, 2) to ChessPieceFactory.buildPawn("PW2", Color.WHITE)),
                (Position(3, 2) to ChessPieceFactory.buildPawn("PW3", Color.WHITE)),
                (Position(4, 2) to ChessPieceFactory.buildPawn("PW4", Color.WHITE)),
                (Position(5, 2) to ChessPieceFactory.buildPawn("PW5", Color.WHITE)),
                (Position(6, 2) to ChessPieceFactory.buildPawn("PW6", Color.WHITE)),
                (Position(7, 2) to ChessPieceFactory.buildPawn("PW7", Color.WHITE)),
                (Position(8, 2) to ChessPieceFactory.buildPawn("PW8", Color.WHITE)),
                (Position(9, 2) to ChessPieceFactory.buildPawn("PW9", Color.WHITE)),
                (Position(10, 2) to ChessPieceFactory.buildPawn("PW10", Color.WHITE)),


                (Position(1, 7) to ChessPieceFactory.buildPawn("PB1", Color.BLACK)),
                (Position(2, 7) to ChessPieceFactory.buildPawn("PB2", Color.BLACK)),
                (Position(3, 7) to ChessPieceFactory.buildPawn("PB3", Color.BLACK)),
                (Position(4, 7) to ChessPieceFactory.buildPawn("PB4", Color.BLACK)),
                (Position(5, 7) to ChessPieceFactory.buildPawn("PB5", Color.BLACK)),
                (Position(6, 7) to ChessPieceFactory.buildPawn("PB6", Color.BLACK)),
                (Position(7, 7) to ChessPieceFactory.buildPawn("PB7", Color.BLACK)),
                (Position(8, 7) to ChessPieceFactory.buildPawn("PB8", Color.BLACK)),
                (Position(9, 7) to ChessPieceFactory.buildPawn("PB9", Color.BLACK)),
                (Position(10, 7) to ChessPieceFactory.buildPawn("PB10", Color.BLACK)),


                (Position(1, 8) to ChessPieceFactory.buildRook("RB1", Color.BLACK)),
                (Position(2, 8) to ChessPieceFactory.buildKnight("KB1", Color.BLACK)),
                (Position(3, 8) to ChessPieceFactory.buildArchBishop("AB", Color.BLACK)),
                (Position(4, 8) to ChessPieceFactory.buildBishop("BB1", Color.BLACK)),
                (Position(5, 8) to ChessPieceFactory.buildQueen("QB", Color.BLACK)),
                (Position(6, 8) to ChessPieceFactory.buildKing("KB", Color.BLACK)),
                (Position(7, 8) to ChessPieceFactory.buildBishop("BB2", Color.BLACK)),
                (Position(8, 8) to ChessPieceFactory.buildChancellor("CB", Color.BLACK)),
                (Position(9, 8) to ChessPieceFactory.buildKnight("KB2", Color.BLACK)),
                (Position(10, 8) to ChessPieceFactory.buildRook("RB2", Color.BLACK)),

                )
            return pieceMap
        }
    }
}
