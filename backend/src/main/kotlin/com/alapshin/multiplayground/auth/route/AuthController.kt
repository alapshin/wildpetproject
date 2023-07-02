package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.users.data.User

interface AuthController {
    fun register(username: String, password: String): User
    fun authenticate(username: String, password: String): Pair<User, String>?
}
