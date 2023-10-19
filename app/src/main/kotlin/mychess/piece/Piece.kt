package mychess.piece

import mychess.movement.MovementValidator
import mychess.piece.Color

class Piece(private val id: String, private val pieceColor: Color, private val movementList: List<MovementValidator>){
    fun getId() : String{
        return id
    }
    fun getPieceColor() : Color {
        return pieceColor
    }
    fun getMovementList(): List<MovementValidator>{
        return movementList
    }

}