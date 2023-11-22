package mychess.movement.endvalidators

import common.board.Board

import common.result.ResultValidator

interface MatchEndingValidator {
    fun validate(board: Board): ResultValidator
}