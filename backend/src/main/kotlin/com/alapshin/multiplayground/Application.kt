package com.alapshin.multiplayground

import com.alapshin.multiplayground.auth.model.UserSession
import com.alapshin.multiplayground.config.ConfigComponent
import com.alapshin.multiplayground.config.create
import com.alapshin.multiplayground.core.RouterManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.db.create
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.session
import io.ktor.server.cio.EngineMain
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.sessions.SessionTransportTransformerEncrypt
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.util.hex
import kotlinx.serialization.json.Json

lateinit var routerManager: RouterManager

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    diModule()
    authModule()
    staticModule()
    routingModule()
    serializationModule()
}

@Suppress("UnusedPrivateProperty")
private fun Application.diModule() {
    val config = environment.config
    val filename = config.propertyOrNull("database.filename")?.getString() ?: ""
    val configComponent = ConfigComponent::class.create(environment.config)
    val databaseComponent = DatabaseComponent::class.create(filename)
    val applicationComponent: ApplicationComponent = ApplicationComponent::class.create(
        configComponent = configComponent,
        databaseComponent = databaseComponent,
    )
    routerManager = applicationComponent.routerManager
}

private fun Application.authModule() {
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

private fun Application.staticModule() {
    routing {
        staticResources("/.well-known/", "well-known")
    }
}

private fun Application.routingModule() {
    routing {
        routerManager.setup(this)
    }
}

private fun Application.serializationModule() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
            },
        )
    }
}
