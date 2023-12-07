package test.common

import edu.austral.dissis.chess.common.board.Position
import edu.austral.dissis.chess.common.factory.ChessPieceFactory
import edu.austral.dissis.chess.common.movementvalidators.Movement
import edu.austral.dissis.chess.common.piece.Color
import edu.austral.dissis.chess.common.result.SuccessfulResult
import junit.framework.TestCase.assertTrue
import org.junit.Test

class BoardTest {

    val testGameGenerator = ChessPieceFactoryTest()

    @Test
    fun test001_BoardNotEmpty() {
        val pawn = ChessPieceFactory.buildPawn("P1", Color.WHITE)
        val gameState = testGameGenerator.generatePieceInTheMiddle(pawn)
        assertTrue(gameState.board.getPositionMap().isNotEmpty())
    }
}