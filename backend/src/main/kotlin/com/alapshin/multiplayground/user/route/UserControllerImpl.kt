package com.alapshin.multiplayground.user.route

import com.alapshin.multiplayground.user.model.User
import com.alapshin.multiplayground.user.model.UserRepository
import me.tatarka.inject.annotations.Inject

@Inject
class UserControllerImpl (private val repository: UserRepository) : UserController {
    override fun getUser(userId: Long): User? {
        return repository.getUser(userId)
    }
}