package com.alapshin.multiplayground.auth.data

import com.alapshin.multiplayground.db.Database
import com.alapshin.multiplayground.db.runGettingLastId
import com.alapshin.multiplayground.user.data.User
import com.password4j.Password
import me.tatarka.inject.annotations.Inject
import java.sql.SQLException

@Inject
class AuthRepositoryImpl constructor(private val database: Database) : AuthRepository {
    override fun registerUser(username: String, password: String): User? {
        val hash = Password.hash(password)
            .addRandomSalt()
            .withArgon2()
        val salt = hash.salt
        val hashedPassword = hash.result
        return try {
            val userId = database.runGettingLastId {
                database.usersQueries.insert(username, hashedPassword, salt)
            }
            database.usersQueries.selectUserByUserId(userId) { userId, username ->
                User(userId, username)
            }.executeAsOne()
        } catch (exception: SQLException) {
            null
        }
    }

    override fun authenticateUser(username: String, password: String): User? {
        val result = database.usersQueries.selectUserByUsername(username)
            .executeAsOneOrNull()

        return if (result == null) {
            null
        } else {
            val verified = Password.check(password, result.password)
                .addSalt(result.salt)
                .withArgon2()
            if (!verified) {
                null
            } else {
                User(userId = result.user_id, username = result.username)
            }
        }
    }
}
