package com.alapshin.multiplayground.user.route

import com.alapshin.multiplayground.core.Router
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.util.getOrFail
import me.tatarka.inject.annotations.Inject

@Inject
class UserRouter constructor(
    private val controller: UserController,
) : Router {
    override fun setup(routing: Routing) {
        routing.apply {
            get("/users/{userId}/") {
                with(call) {
                    val userId = parameters.getOrFail<Long>("userId")
                    val user = controller.getUser(userId)
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
