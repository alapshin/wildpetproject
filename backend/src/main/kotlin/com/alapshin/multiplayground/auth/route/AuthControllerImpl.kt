package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.jwt.JwtCreator
import com.alapshin.multiplayground.jwt.JwtCreatorImpl
import com.alapshin.multiplayground.user.model.User
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Inject

@Inject
class AuthControllerImpl constructor(
    private val jwtCreator: JwtCreator,
    private val repository: AuthRepository
) : AuthController {
    override fun authenticate(username: String, password: String): Pair<User, String>? {
        val user = repository.loginUser(username, password)
        return if (user == null) {
            null
        } else {
            Pair(user, jwtCreator.create(user.username))
        }
    }
    override fun register(username: String, password: String): User {
        return repository.registerUser(username, password)
    }
}
