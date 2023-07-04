package com.alapshin.multiplayground.user.data

interface UserRepository {
    fun getUser(userId: Long): User?
}
