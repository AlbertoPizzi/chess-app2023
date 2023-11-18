package clientserver.netty

//import edu.austral.ingsis.clientserver.*
//import edu.austral.ingsis.clientserver.Server
//import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
//import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
//import edu.austral.ingsis.clientserver.util.ClientConnectionCollectorListener
//import edu.austral.ingsis.clientserver.util.ServerConnectionCollectorListener
//import org.junit.jupiter.api.BeforeEach
//import org.testng.Assert.assertEquals
//import org.testng.annotations.BeforeTest
//import org.testng.annotations.Test
//import java.net.InetSocketAddress
//
//
//class NettyServerConnectionTest {
//    companion object {
//        private const val HOST = "localhost"
//        const val PORT = 10_000
//
//        val ADDRESS = InetSocketAddress(HOST, PORT)
//    }
//
//    private val serverConnectionListener = ServerConnectionCollectorListener()
//    private val clientConnectionListener = ClientConnectionCollectorListener()
//
//    private fun createServer(): Server = NettyServerBuilder.createDefault()
//        .withPort(PORT)
//        .withConnectionListener(serverConnectionListener)
//        .build()
//
//    private fun createClient(): Client = NettyClientBuilder.createDefault()
//        .withAddress(ADDRESS)
//        .withConnectionListener(clientConnectionListener)
//        .build()
//
//    @BeforeTest
//    fun clearListeners() {
//        serverConnectionListener.clear()
//        clientConnectionListener.clear()
//    }
//
//    @Test
//    fun `start and stop server - should release the port`() {
//        // Creates server
//        val server1 = createServer()
//        server1.start()
//
//        // Keeps it up for a few milliseconds
//        Thread.sleep(50)
//
//        // Then kills it
//        server1.stop()
//
//        // Repeats the process
//        val server2 = createServer()
//        server2.start()
//        Thread.sleep(50)
//        server2.stop()
//    }
//
//    @Test
//    fun `start and stop server with connections - should release the port`() {
//        // Creates server
//        val server1 = createServer()
//        server1.start()
//        // Creates client
//        val client = createClient()
//        client.connect()
//
//        // Checks connections
//        Thread.sleep(200)
//        assertEquals(1, serverConnectionListener.clients.size)
//        assertEquals(true, clientConnectionListener.isConnected)
//
//        // Then kills it
//        client.closeConnection()
//        server1.stop()
//
//        // Checks connections
//        Thread.sleep(50)
//        assertEquals(0, serverConnectionListener.clients.size)
//        assertEquals(false, clientConnectionListener.isConnected)
//
//        // Repeats the process
//        val server2 = createServer()
//        server2.start()
//        Thread.sleep(50)
//        server2.stop()
//    }
//}