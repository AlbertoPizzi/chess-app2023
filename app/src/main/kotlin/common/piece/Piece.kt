package common.piece

import common.movementvalidators.MovementValidator

class Piece(private val type: PieceType ,private val id: String, private val pieceColor: Color, private val movementList: List<MovementValidator>){
    fun getId() : String{
        return id
    }
    fun getPieceColor() : Color {
        return pieceColor
    }
    fun getMovementList(): List<MovementValidator>{
        return movementList
    }
    fun getPieceType():PieceType{
        return type
    }

}