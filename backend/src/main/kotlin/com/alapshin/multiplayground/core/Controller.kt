package com.alapshin.multiplayground.core

import io.ktor.server.routing.Routing

interface Controller {
    fun setup(routing: Routing)
}
