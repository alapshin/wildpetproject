package com.alapshin.multiplayground.auth.data

import com.alapshin.multiplayground.user.data.User

interface AuthRepository {
    fun loginUser(username: String, password: String): User?
    fun registerUser(username: String, password: String): User
}
