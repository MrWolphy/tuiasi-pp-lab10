package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler de la baza lanțului — HappyWorker.
 *
 * Procesează cererea finală și generează răspunsul `"Response - <mesaj>"`.
 * Nu delegă mai departe (este ultimul în lanț).
 */
class HappyWorkerHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    override suspend fun handleRequest(message: String): String {
        delay(200) // lucrătorul are nevoie de mai mult timp
        println("[HappyWorker] Procesez: $message")
        val raspuns = "Response - $message"
        println("[HappyWorker] Răspund: $raspuns")
        return raspuns
    }
}
