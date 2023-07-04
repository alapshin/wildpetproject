package com.alapshin.multiplayground.user.presentation

import com.alapshin.multiplayground.config.Constants
import com.alapshin.multiplayground.core.Controller
import com.alapshin.multiplayground.user.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.util.getOrFail
import me.tatarka.inject.annotations.Inject

@Inject
class UserControler constructor(
    private val service: UserService,
) : Controller {
    override fun setup(routing: Routing) {
        routing.apply {
            authenticate(Constants.JWT_AUTH_NAME) {
                get("/users/{userId}/") {
                    with(call) {
                        val userId = parameters.getOrFail<Long>("userId")
                        val user = service.getUser(userId)
                        if (user != null) {
                            respond(HttpStatusCode.OK, user)
                        } else {
                            respond(HttpStatusCode.NotFound)
                        }
                    }
                }
            }
        }
    }
}
