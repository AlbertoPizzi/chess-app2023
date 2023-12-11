package edu.austral.dissis.chess.mychess.movement.composedmovement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.game.GameState
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.movementvalidators.MovementValidator
import edu.austral.dissis.chess.common.movementvalidators.PieceMover
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.ResultValidator
import edu.austral.dissis.chess.common.result.SuccessfulResult
import edu.austral.dissis.chess.common.rules.Game

class NotInCheckMV : MovementValidator {
    override fun validateMovement(game: Game, movement: Movement): ResultValidator {
        val gameState = game.getGameState()
        val board = gameState.board
        val newGameState = PieceMover().moveTo(game, movement)
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

    private fun isPieceColorTargetingPosition(target: Position, game: Game, color: Color): Boolean {
        val gameState = game.getGameState()
        val board = gameState.board
        val pieceFilterList =
            board.getPositionMap().values.filter { it.pieceColor == color && it.id != "KW" && it.id != "KB" }
        for (piece in pieceFilterList) {
            val initPos = board.getPositionByPiece(piece)
            if (piece.mv[0].validateMovement(game, Movement(initPos, target)) is SuccessfulResult) {
                return true
            }
        }
        return false
    }
}