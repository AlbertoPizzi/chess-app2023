package mychess.board

import mychess.exceptions.PieceNotFoundException
import mychess.piece.Piece

class RegularBoard(
    private val boardType: BoardType,
    private val colSize: Int,
    private val rowSize: Int,
    private val positionMap: Map<Position, Piece>,
    private val positionList: List<Position>
) : Board {
    override fun getBoardType(): BoardType {
        return boardType
    }

    override fun getColSize(): Int {
       return colSize
    }

    override fun getRowSize(): Int {
        return rowSize
    }

    override fun getPositionMap(): Map<Position, Piece> {
        return positionMap
    }

    override fun isInBounds(position: Position): Boolean {
        return (position.column in 0 until colSize) && (position.row in 0 until rowSize)
    }

    override fun getPositionByPiece(piece: Piece): Position {
        for( i in positionMap.keys){
            if(piece == positionMap[i]){
                return i
            }
        }
        throw PieceNotFoundException("Piece not found!")
    }

    override fun getPositions(): List<Position> {
        return positionList
    }

    override fun getPieceByPosition(position: Position): Piece? {
       val target : Piece? = positionMap.getValue(position)
        return target
    }

}
