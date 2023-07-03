package com.alapshin.multiplayground.jwt

interface JwtCreator {
    fun create(subject: String): String
}