package com.alapshin.multiplayground.login.data

import me.tatarka.inject.annotations.Inject

@Inject
class LoginManagerImpl(
    private val service: LoginService,
) : LoginManager {
    override suspend fun login(username: String, password: String): LoginResponse {
        return service.login(LoginRequest(username = username, password = password))
    }
}
