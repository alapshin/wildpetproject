package com.alapshin.multiplayground.user.service

import com.alapshin.multiplayground.user.data.User
import com.alapshin.multiplayground.user.data.UserRepository
import me.tatarka.inject.annotations.Inject

@Inject
class UserServiceImpl(private val repository: UserRepository) : UserService {
    override fun getUser(userId: Long): User? {
        return repository.getUser(userId)
    }
}
