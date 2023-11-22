package mychess.movement.endvalidators

import mychess.board.Board

import common.result.ResultValidator

interface MatchEndingValidator {
    fun validate(board: Board): ResultValidator
}