package com.alapshin.multiplayground.jwt

import com.alapshin.multiplayground.config.Config
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Inject

@Inject
class JwtCreatorImpl constructor(private val config: ApplicationConfig) : JwtCreator {
    val secret = config.property(Config.JWT_SECRET).getString()
    val issuer = config.property(Config.JWT_ISSUER).getString()
    val audience = config.property(Config.JWT_AUDIENCE).getString()

    override fun create(subject: String): String {
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withSubject(subject)
            .sign(Algorithm.HMAC256(secret))
    }
}
