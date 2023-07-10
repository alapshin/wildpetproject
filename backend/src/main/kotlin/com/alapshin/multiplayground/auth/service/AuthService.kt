package com.alapshin.multiplayground.auth.service

import com.alapshin.multiplayground.user.data.User

interface AuthService {
    fun registerUser(username: String, password: String): User?
    fun authenticateUser(username: String, password: String): Pair<User, String>?
}
