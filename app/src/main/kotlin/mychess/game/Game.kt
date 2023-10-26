package mychess.game

import mychess.Adapter
import mychess.board.Board
import mychess.board.BoardType
import mychess.factory.BoardFactory
import mychess.movement.Movement
import mychess.movement.PieceMover
import mychess.piece.Color
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult
import java.util.Scanner

class Game {
    private val sc = Scanner(System.`in`)
    private val pieceMover = PieceMover()
    private val adapter = Adapter()

    fun getAdapter(): Adapter{
        return adapter
    }
    fun init(): GameState{
        val board = BoardFactory.createNewBoard(BoardType.REGULAR)
        val boardHistory : List<Board> = createHistoryFromBoard(board)
        val turnManager : TurnManager = ClassicTurn(Color.WHITE)
        val gameState = GameState(turnManager , boardHistory)
        adapter.saveHistory(gameState)
        return gameState
    }
    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }
    fun applyMove(movement: Movement, gameState: GameState) : ResultValidator {
        val pieceToMove  = gameState.getBoardHistory().last().getPieceByPosition(movement.initpos)
        val turnStrategy : TurnManager = gameState.getTurnManager()
        if (pieceToMove.getPieceColor() != turnStrategy.getCurrentPlayer()){
            return FailureResult("It's " + turnStrategy.getCurrentPlayer() + "'s Turn!")
        }else{
            val newBoard : Board = pieceMover.moveTo(pieceToMove , movement.finalpos , gameState.getBoardHistory().last())
            if (newBoard == gameState.getBoardHistory().last()){
                return FailureResult("Invalid movement for " +
                        pieceToMove.getId().takeWhile { it.isLetter() })
            }
            val kingColor = pieceToMove.getPieceColor()
//            val kingPosition : Position = findKingPosition(newBoard, kingColor)

//            // Verificar si el rey del jugador actual está en jaque en el nuevo tablero
//            val kingInCheckValidator = KingInCheckValidator()
//            val checkMateValidator = CheckMateValidator()
//            if (kingInCheckValidator.isKingInCheck(newBoard, kingPosition, kingColor)){
//                return InvalidMovement("Tu rey está en jaque")
//            }

            val history : List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.nextTurn(pieceToMove.getPieceColor())
            adapter.saveHistory(GameState(advanceTurn, history))

            // Verificar si el jugador actual está en jaque mate
//            if (checkMateValidator.validateMovement(history.last(), kingPosition, kingColor, movement)){
//                return GameOver("Jaque Mate!")
//            }

            return SuccessfulResult("It is a valid Move")
        }
    }
}