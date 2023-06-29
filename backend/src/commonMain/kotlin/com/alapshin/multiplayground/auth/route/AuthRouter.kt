package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.auth.model.Credentials
import com.alapshin.multiplayground.core.Router
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRouter(
    private val controller: AuthController,
) : Router {
    override fun setup(routing: Routing) {
        routing.apply {
            post("/auth/login/") {
            }

            post("/auth/register/") {
                with(call) {
                    val credentials = receive<Credentials>()
                    val user = controller.register(
                        username = credentials.username,
                        password = credentials.password,
                    )
                    respond(HttpStatusCode.OK, user)
                }
            }
        }
    }
}
