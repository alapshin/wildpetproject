package com.alapshin.multiplayground.login.data

interface LoginManager {
    suspend fun login(email: String, password: String): LoginResponse
}
