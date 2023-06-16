package com.alapshin.multiplayground.login.data

interface LoginManager {
    suspend fun login(username: String, password: String): LoginResponse
}
