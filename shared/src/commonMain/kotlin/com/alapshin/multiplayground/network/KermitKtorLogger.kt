package com.alapshin.multiplayground.network

import io.ktor.client.plugins.logging.Logger

object KermitKtorLogger : Logger {
    private const val TAG = "HttpLogger"

    override fun log(message: String) {
        co.touchlab.kermit.Logger.i(tag = TAG) {
            message
        }
    }
}
