package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.users.data.User

interface AuthController {
    fun register(username: String, password: String): User
}
