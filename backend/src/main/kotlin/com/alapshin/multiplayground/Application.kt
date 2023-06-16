package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.Credentials
import com.alapshin.multiplayground.auth.UserSession
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.session
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.receive
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.SessionTransportTransformerEncrypt
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.util.hex
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureAuth()
    configureRouting()
    configureSerialization()
}

private fun Application.configureAuth() {
    install(Sessions) {
        val secretSignKey = hex("6819b57a326945c1968f45236589")
        val secretEncryptKey = hex("00112233445566778899aabbccddeeff")
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }
    install(Authentication) {
        session<UserSession> {
            validate { session ->
                session
            }
            challenge {
                call.respond(HttpStatusCode.Unauthorized)
            }
        }
    }
}

private fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/") {
            call.respond(HttpStatusCode.OK)
        }
        post("/login/") {
            val credentials = call.receive<Credentials>()
            if (credentials.username.isNotEmpty() && credentials.password.isNotEmpty()) {
                call.sessions.set(UserSession(credentials.username))
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

private fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
            }
        )
    }
}
