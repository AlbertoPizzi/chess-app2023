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

class RookMVTest {
    val testGameGenerator = ChessPieceFactoryTest()
    @Test
    fun test001_RookMovement() {
        val rook = ChessPieceFactory.buildRook("R1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(rook)
        val movement = Movement( gameState.board.getPositionByPiece(rook) , Position(5, 6))
        TestCase.assertTrue(rook.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
    @Test
    fun test002_RookCannotMoveDiagonally() {
        val rook = ChessPieceFactory.buildRook("R1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddleWithEnemy(rook)
        val movement = Movement( gameState.board.getPositionByPiece(rook) , Position(6, 6))
        TestCase.assertTrue(rook.mv[0].validateMovement(gameState, movement) is FailureResult)
    }
    @Test
    fun test003_RookHorizontalMovement() {
        val rook = ChessPieceFactory.buildRook("R1", Color.WHITE)
        val gameState = testGameGenerator.generateWithAlly(rook)
        val movement = Movement( gameState.board.getPositionByPiece(rook) , Position(8 , 5))
        TestCase.assertTrue(rook.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
    @Test
    fun test004_RookVerticalMovement(){
        val rook = ChessPieceFactory.buildRook("R1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(rook)
        val movement = Movement( gameState.board.getPositionByPiece(rook) , Position(5 , 8))
        TestCase.assertTrue(rook.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
}