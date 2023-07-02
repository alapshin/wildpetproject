package com.alapshin.multiplayground.core

import io.ktor.server.routing.Routing

interface Router {
    fun setup(routing: Routing)
}
