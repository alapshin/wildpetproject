package com.alapshin.multiplayground

import com.alapshin.multiplayground.users.data.User
import com.alapshin.multiplayground.users.data.UserRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

val users = listOf(
    User(1, "john"),
    User(2, "mark"),
    User(3, "pavel")
)

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/hello") {
            call.respondText("Hello")
        }
        get<UserRoutes> {
            call.respond(users)
        }
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
            }
        )
    }
}
