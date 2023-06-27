package com.alapshin.multiplayground.auth.model

import com.alapshin.multiplayground.db.Database
import com.alapshin.multiplayground.users.data.User
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRepositoryImpl constructor(private val database: Database) : AuthRepository {
    override fun loginUser(username: String, password: String) {
    }

    override fun registerUser(username: String, password: String): User {
        database.usersQueries.insert(username, password)
        return User(0, username = username)
    }
}