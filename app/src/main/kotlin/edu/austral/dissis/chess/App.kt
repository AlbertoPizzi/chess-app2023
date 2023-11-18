
package edu.austral.dissis.chess
import edu.austral.dissis.chess.*
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage
import mychess.game.ApeEngine


fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val gameEngine = ApeEngine()
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(imageResolver)
        primaryStage.scene = Scene(root)

//        root.addListener(GameEventListener)
        primaryStage.show()
    }
}