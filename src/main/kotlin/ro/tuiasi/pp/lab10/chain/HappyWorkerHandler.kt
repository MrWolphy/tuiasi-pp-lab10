package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler de la baza lanțului — HappyWorker.
 *
 * Generează răspunsul final `"Response - <mesaj>"`; nu delegă mai departe.
 */
class HappyWorkerHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    override suspend fun handleRequest(message: String): String {
        delay(200) // lucrătorul muncește mai mult
        println("[HappyWorker] Procesez: $message")
        val raspuns = "Response - $message"
        println("[HappyWorker] Răspund: $raspuns")
        return raspuns
    }
}
