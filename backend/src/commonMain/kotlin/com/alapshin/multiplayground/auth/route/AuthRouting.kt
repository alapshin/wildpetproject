package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.Database
import com.alapshin.multiplayground.auth.model.Credentials
import com.alapshin.multiplayground.users.data.User
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

fun Routing.setupAuthRouting(database: Database) {
    post("/auth/login/") {
        with(call) {
            val credentials = receive<Credentials>()
            val user = database.usersQueries
                .select(
                    username = credentials.username,
                    password = credentials.password
                ) { userId, username ->
                    User(userId, username)
                }
                .executeAsOneOrNull()
            if (user != null) {
                respond(HttpStatusCode.OK, user)
            } else {
                respond(HttpStatusCode.NotFound)
            }
        }
    }

    post("/auth/register/") {
        with(call) {
            val credentials = receive<Credentials>()
            database.usersQueries.insert(credentials.username, credentials.password)
            respond(HttpStatusCode.OK)
        }
    }
}