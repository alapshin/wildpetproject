package com.alapshin.multiplayground.user.route

import com.alapshin.multiplayground.user.model.User

interface UserController {
    fun getUser(userId: Long): User?
}
