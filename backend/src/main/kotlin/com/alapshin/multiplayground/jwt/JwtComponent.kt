package com.alapshin.multiplayground.jwt

import com.alapshin.multiplayground.config.Config
import com.alapshin.multiplayground.config.ConfigComponent
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class JwtComponent(@Component val configComponent: ConfigComponent) {
    abstract val jwtVerifier: JWTVerifier

    @Provides
    fun jwtCreator(jwtCreator: JwtCreatorImpl): JwtCreator = jwtCreator

    @Provides
    fun jwtVerifier(config: ApplicationConfig): JWTVerifier {
        val secret = config.property(Config.JWT_SECRET).getString()
        val issuer = config.property(Config.JWT_ISSUER).getString()
        val audience = config.property(Config.JWT_AUDIENCE).getString()

        return JWT
            .require(Algorithm.HMAC256(secret))
            .withIssuer(issuer)
            .withAudience(audience)
            .build()
    }
}