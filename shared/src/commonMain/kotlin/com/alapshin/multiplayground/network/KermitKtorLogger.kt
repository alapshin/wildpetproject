package com.alapshin.multiplayground.network

import io.ktor.client.plugins.logging.Logger

object KermitKtorLogger : Logger {
    private const val TAG = "HttpLogger"
    private val logger = co.touchlab.kermit.Logger.withTag("Ktor")

    override fun log(message: String) {
        logger.i { message }
    }
}
