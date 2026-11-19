package ro.tuiasi.pp.lab10.factory

/**
 * Factory producer — returnează factory-ul potrivit în funcție de tipul cerut.
 * Tipuri acceptate (case-insensitive): `"ELITE"`, `"HAPPY_WORKER"`.
 */
object FactoryProducer {
    fun getFactory(type: String): Factory = when (type.uppercase()) {
        "ELITE" -> EliteFactory()
        "HAPPY_WORKER" -> HappyWorkerFactory()
        else -> throw IllegalArgumentException("Tip factory necunoscut: $type")
    }
}
