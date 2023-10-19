package piece

import movement.MovementValidator
import movement.composedmovement.RookMV

class Piece(private val id: String, private val pieceColor: Color, val movementList: List<MovementValidator>){
    fun getId() : String{
        return id
    }
    fun getPieceColor() : Color{
        return pieceColor
    }
    fun getMovementList(): List<MovementValidator>{
        return movementList
    }

}