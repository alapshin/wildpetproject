package com.alapshin.multiplayground.auth.data

import com.alapshin.multiplayground.user.data.User

interface AuthRepository {
    fun registerUser(username: String, password: String): User?
    fun authenticateUser(username: String, password: String): User?
}
