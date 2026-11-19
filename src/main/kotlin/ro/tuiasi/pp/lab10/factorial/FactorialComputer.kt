package ro.tuiasi.pp.lab10.factorial

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

/** Numărul de corutine consumatoare. */
private const val NR_CONSUMATORI = 4

/**
 * Calculează factorial pentru fiecare valoare din [values] folosind 4 corutine paralele
 * care consumă dintr-un canal de lucru comun.
 */
suspend fun computeFactorials(values: List<Int>): Map<Int, Long> = coroutineScope {
    val canal = Channel<Int>(Channel.BUFFERED)

    // producătorul: pune toate valorile în coada de lucru
    launch {
        for (v in values) canal.send(v)
        canal.close()
    }

    // consumatorii rulează pe fire diferite -> map thread-safe
    val rezultate = ConcurrentHashMap<Int, Long>()

    val consumatori = (1..NR_CONSUMATORI).map { id ->
        async(Dispatchers.Default) {
            for (n in canal) {
                val f = factorial(n)
                rezultate[n] = f
                println("[Consumator $id] $n! = $f")
            }
        }
    }
    consumatori.forEach { it.await() }

    rezultate
}

/**
 * Calculează n! iterativ. Pentru n > 20 rezultatul depășește Long.
 */
fun factorial(n: Int): Long {
    require(n >= 0) { "Factorial nedefinit pentru n negativ: $n" }
    return (1..n).fold(1L) { acc, i -> acc * i }
}
