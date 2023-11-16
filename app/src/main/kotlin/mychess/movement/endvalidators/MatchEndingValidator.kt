package mychess.movement.endvalidators

import mychess.board.Board

import commons.result.ResultValidator

interface MatchEndingValidator{
    fun validate(board : Board) : ResultValidator
}