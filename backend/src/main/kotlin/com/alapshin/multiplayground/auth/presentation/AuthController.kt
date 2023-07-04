package com.alapshin.multiplayground.auth.presentation

import com.alapshin.multiplayground.auth.service.AuthService
import com.alapshin.multiplayground.core.Controller
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import me.tatarka.inject.annotations.Inject

@Inject
class AuthController(
    private val service: AuthService,
) : Controller {
    override fun setup(routing: Routing) {
        routing.apply {
            post("/auth/login/") {
                with(call) {
                    val (username, password) = receive<Credentials>()
                    val pair = service.authenticate(
                        username = username,
                        password = password,
                    )
                    if (pair == null) {
                        respond(HttpStatusCode.NotFound)
                    } else {
                        respond(HttpStatusCode.OK, pair.first)
                        response.header(HttpHeaders.Authorization, pair.second)
                    }
                }
            }

            post("/auth/register/") {
                with(call) {
                    val credentials = receive<Credentials>()
                    val user = service.register(
                        username = credentials.username,
                        password = credentials.password,
                    )
                    respond(HttpStatusCode.OK, user)
                }
            }
        }
    }
}
