package server

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import edu.austral.dissis.chess.common.Adapter
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
import edu.austral.dissis.chess.common.game.ApeEngine
import edu.austral.dissis.chess.common.gamestates.InProgressStateResult
import edu.austral.dissis.chess.common.gamestates.InvalidMoveStateResult
import edu.austral.dissis.chess.common.gamestates.WinStateResult
import edu.austral.dissis.chess.common.rules.Game

class GameServer(val game: Game) {

    private var rules = game
    private val adapter = Adapter()

    private val server: Server = NettyServerBuilder.createDefault().withPort(8080)
        .addMessageListener("move", jacksonTypeRef(), MovementListener(this))
        .addMessageListener("init", jacksonTypeRef(), InitialServerListener(this))
        .build()

    private val gameEngine = ApeEngine.chessEngine()
    fun handleMove(message: Message<Move>) {
        val newMove = adapter.translateMovement(message.payload)
        when (val moveResult = rules.applyMove(rules,newMove)) {
            is InProgressStateResult -> {
                this.rules = moveResult.game
                server.broadcast(Message("new-game-state",adapter.adaptGameState(moveResult.game)))
            }
            is WinStateResult -> server.broadcast(Message("game-over",moveResult.winner))
            is InvalidMoveStateResult -> server.broadcast(Message("invalid",moveResult.message))
        }
    }

    fun handleInit() {
        server.broadcast(Message("init", gameEngine))
    }


    fun startServer() {
        server.start()
    }

    fun stopServer() {
        server.stop()
    }
}