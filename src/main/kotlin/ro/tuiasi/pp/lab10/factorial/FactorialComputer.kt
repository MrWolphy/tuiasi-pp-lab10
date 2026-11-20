package ro.tuiasi.pp.lab10.factorial

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

/**
 * Calcul factorial paralel: un canal de lucru + 4 corutine consumatoare.
 */

const val NR_CONSUMATORI = 4

/**
 * Calculează factorial pentru fiecare valoare din [values] folosind 4 corutine paralele.
 *
 * @return Map cu perechile n → n!
 */
suspend fun computeFactorials(values: List<Int>): Map<Int, Long> = coroutineScope {
    val canal = Channel<Int>(Channel.BUFFERED)

    // producătorul pune toate valorile în coada de lucru
    launch {
        for (v in values) canal.send(v)
        canal.close()
    }

    // consumatorii rulează pe Dispatchers.Default => chiar în paralel,
    // deci map-ul trebuie să fie thread-safe
    val rezultate = ConcurrentHashMap<Int, Long>()
    val consumatori = (1..NR_CONSUMATORI).map { id ->
        async(Dispatchers.Default) {
            for (n in canal) {
                rezultate[n] = factorial(n)
                println("[Consumator $id] $n! = ${rezultate[n]}")
            }
        }
    }
    consumatori.awaitAll()

    rezultate
}

/**
 * Calculează n! iterativ. Pentru n > 20 rezultatul depășește Long.
 */
fun factorial(n: Int): Long = (1..n).fold(1) { acc, i -> acc * i }
