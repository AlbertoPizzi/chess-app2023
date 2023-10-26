package mychess.movement.composedmovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.*
import mychess.piece.Color
import mychess.piece.Piece
import mychess.piece.PieceType
import mychess.result.FailureResult
import mychess.result.ResultValidator
import mychess.result.SuccessfulResult

class PawnMV : MovementValidator {
    private val verticalMV : MovementValidator = VerticalMV() //moves
    private val diagonalMV : MovementValidator = DiagonalMV() //eats
    private val eatMV: MovementValidator = EatMV()
    private val colorCheck : MovementValidator = ColorMV() //checks Color
    private val pathIsFreeMV : MovementValidator = PathIsFreeMV() //checks free path

    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        if(verticalMV.validateMovement(board , movement) is SuccessfulResult ||
            diagonalMV.validateMovement(board, movement) is SuccessfulResult ){
            //first move
            if(checkFirstMove(board , movement)){
                if(checkWhiteMovement(board, movement) is FailureResult && movement.finalpos.row == movement.initpos.row + 1
                    || movement.finalpos.row == movement.initpos.row + 2){
                    return SuccessfulResult("It's a valid move")
                }
                if(checkWhiteMovement(board , movement) is SuccessfulResult && movement.finalpos.row == movement.initpos.row - 1
                    || movement.finalpos.row == movement.initpos.row - 2 ){
                    return SuccessfulResult("It's a valid move")
                }
            }
            //eat
            if(diagonalMV.validateMovement(board, movement) is SuccessfulResult && eatMV.validateMovement(board, movement) is SuccessfulResult){
                return SuccessfulResult("It's a valid move")
            }
            if(verticalMV.validateMovement(board, movement) is SuccessfulResult){
                if(checkWhiteMovement(board, movement) is SuccessfulResult && movement.finalpos.row == movement.initpos.row + 1){
                    return SuccessfulResult("It's a valid move")
                }
                if(checkWhiteMovement(board , movement) is FailureResult && movement.finalpos.row == movement.initpos.row - 1){
                    return SuccessfulResult("It's a valid move")
                }
            }
        }
        return FailureResult("It's not a valid Pawn move")
    }
    fun checkFirstMove(board: Board , movement: Movement) : Boolean {
        //White
        if(board.getPieceByPosition(movement.initpos)!!.getPieceColor() == Color.WHITE
            && movement.initpos.row == 2 ){
            return true
        }
        //black
        if(board.getPieceByPosition(movement.initpos)!!.getPieceColor() == Color.BLACK
            && movement.initpos.row == 7){
            return true
        }
        return false
    }
    fun checkWhiteMovement(board: Board , movement: Movement) : ResultValidator{
        if(board.getPieceByPosition(movement.initpos)!!.getPieceColor().equals(Color.WHITE)){
            return SuccessfulResult("It's a white piece!")
        }
        else return FailureResult("It's a black piece!")
    }
}