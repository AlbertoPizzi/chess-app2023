package mychess.movement.endvalidators

import mychess.board.Board

import mychess.result.ResultValidator

interface MatchEndingValidator{
    fun validate(board : Board) : ResultValidator
}