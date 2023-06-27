package com.alapshin.multiplayground.auth.route

import com.alapshin.multiplayground.auth.model.AuthRepository
import com.alapshin.multiplayground.users.data.User
import me.tatarka.inject.annotations.Inject

@Inject
class AuthControllerImpl constructor(private val repository: AuthRepository) : AuthController {
    override fun register(username: String, password: String): User {
        return repository.registerUser(username, password)
    }
}