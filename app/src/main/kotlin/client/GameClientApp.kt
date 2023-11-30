package client

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    Application.launch(ChessClientApp::class.java)
}

class ChessClientApp : Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle
        val root = GameView(imageResolver)
        val scene = Scene(root , 800.0, 600.0)
        primaryStage.scene = scene
        primaryStage.width = 620.0
        primaryStage.height = 800.0
        val client = GameClient(root)
        root.addListener(MovementListener(client))
        primaryStage.show()
    }
}