package com.alapshin.multiplayground.jwt

import io.jsonwebtoken.Jwts
import me.tatarka.inject.annotations.Inject

@Inject
actual class TokenCreator {
    actual fun createToken(subject: String): String {
        return Jwts.builder()
            .setSubject(subject)
            .compact()
    }
}