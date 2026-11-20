package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Handler-ul de nivel cel mai înalt din lanț — CEO.
 *
 * Inițiază cererea pe lanțul descendent cu formatul `"Request - <mesaj>"`
 * și primește răspunsul final de la baza lanțului.
 */
class CEOHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    override suspend fun handleRequest(message: String): String = coroutineScope {
        delay(100)
        val cerere = "Request - $message"
        println("[CEO] Trimit cererea: $cerere")
        // cererea coboară pe lanț într-o corutină separată
        val raspuns = async { next?.handleRequest(cerere) ?: cerere }.await()
        println("[CEO] Am primit răspunsul: $raspuns")
        raspuns
    }
}
