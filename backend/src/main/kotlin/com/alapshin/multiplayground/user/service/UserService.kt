package com.alapshin.multiplayground.user.service

import com.alapshin.multiplayground.user.data.User

interface UserService {
    fun getUser(userId: Long): User?
}
