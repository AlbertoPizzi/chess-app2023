package test.movement

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.FailureResult
import edu.austral.dissis.chess.common.result.SuccessfulResult
import org.junit.Test
import test.common.ChessPieceFactoryTest

class KnightMVTest {
    val testGameGenerator = ChessPieceFactoryTest()
    @Test
    fun test001_KnightMovement() {
        val knight = ChessPieceFactory.buildKnight("N1" , Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(knight)
        val movement = Movement( gameState.board.getPositionByPiece(knight) , Position(6, 7))
        assert(knight.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
    @Test
    fun test002_KnightMovementWithEnemy() {
        val knight = ChessPieceFactory.buildKnight("N1" , Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddleWithEnemy(knight)
        val movement = Movement( gameState.board.getPositionByPiece(knight) , Position(6, 7))
        assert(knight.mv[0].validateMovement(gameState, movement) is SuccessfulResult)
    }
    @Test
    fun test003_KnightMovementWithAlly() {
        val knight = ChessPieceFactory.buildKnight("N1" , Color.WHITE)
        val gameState = testGameGenerator.generateWithAlly(knight)
        val movement = Movement( gameState.board.getPositionByPiece(knight) , Position(4, 6))
        assert(knight.mv[0].validateMovement(gameState, movement) is FailureResult)
    }
}