package com.alapshin.multiplayground

import com.alapshin.multiplayground.config.ConfigComponent
import com.alapshin.multiplayground.config.Constants
import com.alapshin.multiplayground.config.create
import com.alapshin.multiplayground.core.ControllerManager
import com.alapshin.multiplayground.db.DatabaseComponent
import com.alapshin.multiplayground.db.create
import com.alapshin.multiplayground.jwt.JwtComponent
import com.alapshin.multiplayground.jwt.create
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.cio.EngineMain
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

lateinit var jwtVerifier: JWTVerifier
lateinit var routerManager: ControllerManager

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
    val jwtComponent = JwtComponent::class.create(configComponent)
    val databaseComponent = DatabaseComponent::class.create(filename)
    val applicationComponent: ApplicationComponent = ApplicationComponent::class.create(
        jwtComponent = jwtComponent,
        configComponent = configComponent,
        databaseComponent = databaseComponent,
    )
    jwtVerifier = jwtComponent.jwtVerifier
    routerManager = applicationComponent.routerManager
}

private fun Application.authModule() {
    install(Authentication) {
        jwt(Constants.JWT_AUTH_NAME) {
            verifier(jwtVerifier)
            validate { credential ->
                if (credential.payload.subject.isNullOrEmpty()) {
                    null
                } else {
                    JWTPrincipal(credential.payload)
                }
            }
            challenge { scheme, realm ->
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
