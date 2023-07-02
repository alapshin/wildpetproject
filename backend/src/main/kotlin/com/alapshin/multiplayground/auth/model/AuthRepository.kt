package com.alapshin.multiplayground.auth.model

import com.alapshin.multiplayground.user.model.User

interface AuthRepository {
    fun loginUser(username: String, password: String): User?
    fun registerUser(username: String, password: String): User
}
