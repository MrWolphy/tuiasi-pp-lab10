package ro.tuiasi.pp.lab10.pipeline

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Procesor pipeline pentru liste de întregi, implementat cu canale Kotlin.
 *
 * Pipeline-ul are 3 etape conectate prin canale:
 * 1. **Înmulțire**: fiecare element × alpha
 * 2. **Sortare**: colectarea tuturor elementelor și sortarea lor
 * 3. **Afișare/colectare**: returnarea listei finale
 */
class PipelineProcessor {

    fun process(input: List<Int>, alpha: Int): List<Int> = runBlocking {
        val canalInmultire = Channel<Int>(Channel.BUFFERED)
        val canalSortat = Channel<Int>(Channel.BUFFERED)

        // Etapa 1: înmulțire cu alpha
        launch {
            for (element in input) {
                canalInmultire.send(element * alpha)
            }
            canalInmultire.close() // altfel etapa 2 așteaptă la infinit
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

        // Etapa 3: afișare și colectare rezultat
        val rezultat = mutableListOf<Int>()
        for (element in canalSortat) {
            println("[Pipeline] $element")
            rezultat.add(element)
        }
        rezultat
    }
}
