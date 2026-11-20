package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Handler de nivel manager — intermediar între Executive și HappyWorker.
 */
class ManagerHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    override suspend fun handleRequest(message: String): String = coroutineScope {
        delay(100)
        println("[Manager] Am primit: $message")
        val raspuns = async { next?.handleRequest(message) ?: message }.await()
        println("[Manager] Răspuns: $raspuns")
        raspuns
    }
}
