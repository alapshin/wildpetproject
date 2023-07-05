package com.alapshin.multiplayground.auth.service

import com.alapshin.multiplayground.user.data.User

interface AuthService {
    fun register(username: String, password: String): User
    fun authenticate(username: String, password: String): Pair<User, String>?
}
