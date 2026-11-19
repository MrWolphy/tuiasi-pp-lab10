package ro.tuiasi.pp.lab10.factory

import ro.tuiasi.pp.lab10.chain.CEOHandler
import ro.tuiasi.pp.lab10.chain.Handler

/**
 * Factory pentru handler-ele de nivel elit (CEO).
 */
class EliteFactory : Factory {
    override fun createHandler(): Handler = CEOHandler()
}
