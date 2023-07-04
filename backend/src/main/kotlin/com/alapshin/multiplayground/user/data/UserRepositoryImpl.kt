package com.alapshin.multiplayground.user.data

import com.alapshin.multiplayground.db.Database
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl constructor(
    private val database: Database,
) : UserRepository {
    override fun getUser(userId: Long): User? {
        return database.usersQueries.selectUserById(userId) { userId, username ->
            User(
                userId = userId,
                username = username,
            )
        }.executeAsOneOrNull()
    }
}
