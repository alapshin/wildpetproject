package com.alapshin.multiplayground.jwt

import io.jsonwebtoken.Jwts
import me.tatarka.inject.annotations.Inject

@Inject
class TokenCreator {
    fun createToken(subject: String): String {
        return Jwts.builder()
            .setSubject(subject)
            .compact()
    }
}