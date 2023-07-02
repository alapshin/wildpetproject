package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.config.Config
import com.alapshin.multiplayground.jwt.TokenCreator
import com.alapshin.multiplayground.user.model.User
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Inject
import kotlin.io.encoding.ExperimentalEncodingApi

@Inject
class AuthControllerImpl constructor(
    private val config: ApplicationConfig,
    private val tokenCreator: TokenCreator,
    private val repository: AuthRepository
) : AuthController {
    override fun authenticate(username: String, password: String): Pair<User, String>? {
        val user = repository.loginUser(username, password)
        if (user == null) {
            return null
        } else {
            val token = tokenCreator.createToken(user.username)
            return Pair(user, token)
        }
    }
    override fun register(username: String, password: String): User {
        return repository.registerUser(username, password)
    }
}
