package com.alapshin.multiplayground.users.data

import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl(private val service: UserService) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return service.getUsers().users
    }
}
