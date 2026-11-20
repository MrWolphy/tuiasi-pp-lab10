package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Handler de nivel executiv — intermediar între CEO și Manager.
 */
class ExecutiveHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    override suspend fun handleRequest(message: String): String = coroutineScope {
        delay(100)
        println("[Executive] Am primit: $message")
        val raspuns = async { next?.handleRequest(message) ?: message }.await()
        println("[Executive] Răspuns: $raspuns")
        raspuns
    }
}
