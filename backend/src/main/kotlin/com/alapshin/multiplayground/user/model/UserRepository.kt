package com.alapshin.multiplayground.user.model

interface UserRepository {
    fun getUser(userId: Long): User?
}