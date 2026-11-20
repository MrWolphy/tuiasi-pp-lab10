package ro.tuiasi.pp.lab10.pipeline

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Procesor pipeline pentru liste de întregi, implementat cu canale Kotlin.
 *
 * Etape: înmulțire × alpha → sortare → colectare,
 * fiecare legată de următoarea printr-un `Channel<Int>`.
 */
class PipelineProcessor {

    fun process(input: List<Int>, alpha: Int): List<Int> = runBlocking {
        val canalInmultire = Channel<Int>(Channel.BUFFERED)
        val canalSortat = Channel<Int>(Channel.BUFFERED)

        // Etapa 1: înmulțire
        launch {
            for (element in input) {
                canalInmultire.send(element * alpha)
            }
            canalInmultire.close() // altfel etapa 2 așteaptă la nesfârșit
        }

        // Etapa 2: sortare (trebuie să aștepte toate elementele)
        launch {
            val temp = mutableListOf<Int>()
            for (element in canalInmultire) {
                temp.add(element)
            }
            temp.sort()
            for (element in temp) {
                canalSortat.send(element)
            }
            canalSortat.close()
        }

        // Etapa 3: colectare / afișare
        val rezultat = mutableListOf<Int>()
        for (element in canalSortat) {
            println("[Pipeline] $element")
            rezultat.add(element)
        }
        rezultat
    }
}
