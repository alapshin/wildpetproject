package com.alapshin.multiplayground.users.data

interface UserRepository {
    suspend fun getUsers(): List<User>
}
