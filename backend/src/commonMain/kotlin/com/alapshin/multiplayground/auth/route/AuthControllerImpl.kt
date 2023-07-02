package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.config.Config
import com.alapshin.multiplayground.jwt.TokenCreator
import com.alapshin.multiplayground.users.data.User
import com.auth0.jwk.JwkProvider
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Inject
import kotlin.io.encoding.ExperimentalEncodingApi

@Inject
class AuthControllerImpl constructor(
    private val config: ApplicationConfig,
    private val tokenCreator: TokenCreator,
    private val repository: AuthRepository
) : AuthController {
    @OptIn(ExperimentalEncodingApi::class)
    override fun authenticate(username: String, password: String): Pair<User, String>? {
        val user = repository.loginUser(username, password)
        if (user == null) {
            return null
        } else {
            val realm  = config.property(Config.JWT_REALM)
            val issuer = config.property(Config.JWT_ISSUER).getString()
            val audience = config.property(Config.JWT_AUDIENCE).getString()

            val keyId = config.property(Config.JWT_KEY_ID).getString()
            val privateKey = config.property(Config.JWT_PRIVATE_KEY).getString()
            val token = tokenCreator.createToken(user.username)
            return Pair(user, token)
        }
    }
    override fun register(username: String, password: String): User {
        return repository.registerUser(username, password)
    }
}
