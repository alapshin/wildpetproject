package com.alapshin.multiplayground.auth.model

import com.alapshin.multiplayground.users.data.User

interface AuthRepository {
    fun loginUser(username: String, password: String)
    fun registerUser(username: String, password: String): User
}