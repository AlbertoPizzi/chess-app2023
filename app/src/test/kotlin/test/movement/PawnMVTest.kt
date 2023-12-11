package test.movement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.SuccessfulResult
import junit.framework.TestCase.assertTrue

import org.junit.Test
import test.common.ChessPieceFactoryTest

class PawnMVTest {
    val testGameGenerator = ChessPieceFactoryTest()
    @Test
    fun test001_PawnMovement() {
        val pawn = ChessPieceFactory.buildPawn("P1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(pawn)
        val movement = Movement( gameState.board.getPositionByPiece(pawn) , Position(5, 6))
        assertTrue(pawn.mv[0].validateMovement(gameState , movement) is SuccessfulResult)
    }
    @Test
    fun test002_TwoFowardPawnMovement() {
        val pawn = ChessPieceFactory.buildPawn("P1", Color.WHITE)
        val gameState = testGameGenerator.generateWithPawnInInitialPosition(pawn)
        val movement = Movement( gameState.board.getPositionByPiece(pawn) , Position(5, 4))
        assertTrue(pawn.mv[0].validateMovement(gameState , movement) is SuccessfulResult)
    }
    @Test
    fun test003_PawnMovementWithEnemy() {
        val pawn = ChessPieceFactory.buildPawn("P1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddleWithEnemy(pawn)
        val movement = Movement( gameState.board.getPositionByPiece(pawn) , Position(6, 6))
        assertTrue(pawn.mv[0].validateMovement(gameState , movement) is SuccessfulResult)
    }
}