package com.alapshin.multiplayground.jwt

expect class TokenCreator() {
    fun createToken(subject: String): String
}