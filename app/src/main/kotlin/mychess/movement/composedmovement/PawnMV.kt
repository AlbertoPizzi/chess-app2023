package mychess.movement.composedmovement

import edu.austral.dissis.chess.gui.Move
import mychess.board.Board
import mychess.movement.Movement
import mychess.movement.MovementValidator
import mychess.movement.concretemovement.ColorMV
import mychess.movement.concretemovement.DiagonalMV
import mychess.movement.concretemovement.PathIsFreeMV
import mychess.movement.concretemovement.VerticalMV
import mychess.piece.Color
import mychess.piece.Piece
import mychess.piece.PieceType
import mychess.result.ResultValidator

class PawnMV : MovementValidator {
    private val verticalMV : MovementValidator = VerticalMV() //moves
    private val diagonalMV : MovementValidator = DiagonalMV() //eats
    private val colorCheck : MovementValidator = ColorMV() //checks Color
    private val pathIsFreeMV : MovementValidator = PathIsFreeMV() //checks free path

    override fun validateMovement(board: Board, movement: Movement): ResultValidator {
        TODO("Not yet implemented")
    }
    fun checkFirstMove(board: Board , movement: Movement) : Boolean {
        //White
        if(board.getPieceByPosition(movement.initpos).getPieceColor() == Color.WHITE
            && movement.initpos.row == 1 ){
            return true
        }
        //black
        if(board.getPieceByPosition(movement.initpos).getPieceColor() == Color.BLACK
            && movement.initpos.row == 6){
            return true
        }
        return false
    }
}