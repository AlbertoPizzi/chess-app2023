package edu.austral.dissis.chess.mychess.movement.endvalidators

import edu.austral.dissis.chess.common.board.Board

import edu.austral.dissis.chess.common.result.ResultValidator

interface MatchEndingValidator {
    fun validate(board: Board): ResultValidator
}