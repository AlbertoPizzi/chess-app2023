package mychess.movement.composedmovement

import common.board.Board
import common.board.Position
import common.game.GameState
import common.movementvalidators.Movement
import common.movementvalidators.MovementValidator
import common.movementvalidators.PieceMover
import common.piece.Color
import common.result.FailureResult
import common.result.ResultValidator
import common.result.SuccessfulResult

class NotInCheckMV : MovementValidator {
    override fun validateMovement(gameState: GameState, movement: Movement): ResultValidator {
        val board = gameState.getBoardHistory().last()
        val auxBoard = PieceMover().moveTo(board.getPieceByPosition(movement.initpos)!!, movement.finalpos , gameState )
        val auxboardHistory = listOf(auxBoard)
        val newGameState = gameState.copy(boardHistory = auxboardHistory)
        when (board.getPieceByPosition(movement.initpos)!!.pieceColor) {
            Color.WHITE -> {
                val designatedPosition = board.getPositionByID("KW")!!
                if (designatedPosition == movement.initpos) {
                    if (isPieceColorTargetingPosition(movement.finalpos, newGameState, Color.BLACK)) {
                        return FailureResult("King is in check")
                    }
                } else if (isPieceColorTargetingPosition(designatedPosition, newGameState, Color.BLACK)) {
                    return FailureResult("King is in check")
                }
                return SuccessfulResult("Valid movement")
            }
            Color.BLACK -> {
                val designatedPosition = board.getPositionByID("KB")!!
                if (designatedPosition == movement.initpos) {
                    if (isPieceColorTargetingPosition(movement.finalpos, newGameState, Color.WHITE)) {
                        return FailureResult("King is in check")
                    }
                } else if (isPieceColorTargetingPosition(designatedPosition, newGameState, Color.WHITE)) {
                    return FailureResult("King is in check")
                }
                return SuccessfulResult("Valid movement")
            }
        }
    }

    private fun isPieceColorTargetingPosition(target: Position, gameState: GameState, color: Color): Boolean {
        val board = gameState.getBoardHistory().last()
        val pieceFilterList =
            board.getPositionMap().values.filter { it.pieceColor == color && it.id != "KW" && it.id != "KB" }
        for (piece in pieceFilterList) {
            val initPos = board.getPositionByPiece(piece)
            if (piece.movement[0].validateMovement(gameState, Movement(initPos, target)) is SuccessfulResult) {
                return true
            }
        }
        return false
    }
}