package test.movement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.SuccessfulResult
import junit.framework.TestCase
import org.junit.Test
import test.common.ChessPieceFactoryTest

class BishopMVTest {
    val testGameGenerator = ChessPieceFactoryTest()
    @Test
    fun test001_BishopDiagonalMovement() {
        val bishop = ChessPieceFactory.buildBishop("B1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(bishop)
        val movement = Movement( gameState.board.getPositionByPiece(bishop) , Position(6, 6))
        TestCase.assertTrue(bishop.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
    @Test
    fun test002_BishopCannotMoveHorizontally() {
        val bishop = ChessPieceFactory.buildBishop("B1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddleWithEnemy(bishop)
        val movement = Movement( gameState.board.getPositionByPiece(bishop) , Position(6, 5))
        TestCase.assertTrue(bishop.mv[0].validateMovement(gameState, movement) is FailureResult)
    }
    @Test
    fun test003_BishopCannotMoveVertically() {
        val bishop = ChessPieceFactory.buildBishop("B1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddleWithEnemy(bishop)
        val movement = Movement( gameState.board.getPositionByPiece(bishop) , Position(5, 6))
        TestCase.assertTrue(bishop.mv[0].validateMovement(gameState, movement) is FailureResult)
    }
    @Test
    fun test004_BishopCannotMoveToAllyPosition() {
        val bishop = ChessPieceFactory.buildBishop("B1", Color.WHITE)
        val gameState = testGameGenerator.generateWithAlly(bishop)
        val movement = Movement( gameState.board.getPositionByPiece(bishop) , Position(4, 4))
        TestCase.assertTrue(bishop.mv[0].validateMovement(gameState, movement) is FailureResult)
    }
}