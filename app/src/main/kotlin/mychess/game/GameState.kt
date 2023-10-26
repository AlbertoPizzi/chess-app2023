package mychess.game

import mychess.board.Board

class GameState(private val turnManager: TurnManager , private val boardHistory : List<Board>) {
    fun getTurnManager(): TurnManager{
        return turnManager
    }
    fun getBoardHistory(): List<Board>{
        return boardHistory
    }
}