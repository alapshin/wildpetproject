package com.alapshin.multiplayground.login.data

import com.alapshin.multiplayground.auth.AuthService
import me.tatarka.inject.annotations.Inject

@Inject
class LoginManagerImpl(
    private val service: AuthService,
) : LoginManager {
    override suspend fun login(username: String, password: String): LoginResponse {
        return service.login(Credentials(username = username, password = password))
    }
}
