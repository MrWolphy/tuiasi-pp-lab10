package ro.tuiasi.pp.lab10.factory

import ro.tuiasi.pp.lab10.chain.HappyWorkerHandler
import ro.tuiasi.pp.lab10.chain.Handler

/**
 * Factory pentru handler-ele de nivel worker.
 */
class HappyWorkerFactory : Factory {
    override fun createHandler(): Handler = HappyWorkerHandler()
}
