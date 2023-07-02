package com.alapshin.multiplayground.auth.model

import com.alapshin.multiplayground.db.Database
import com.alapshin.multiplayground.db.runGettingLastId
import com.alapshin.multiplayground.user.model.User
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRepositoryImpl constructor(private val database: Database) : AuthRepository {
    override fun loginUser(username: String, password: String): User? {
        return database.usersQueries.select(username, password) { userId, username ->
            User(userId, username)
        }.executeAsOneOrNull()
    }

    override fun registerUser(username: String, password: String): User {
        val userId = database.runGettingLastId {
            database.usersQueries.insert(username, password)
        }
        return database.usersQueries.selectUserById(userId) { userId, username ->
            User(userId, username)
        }.executeAsOne()
    }
}
