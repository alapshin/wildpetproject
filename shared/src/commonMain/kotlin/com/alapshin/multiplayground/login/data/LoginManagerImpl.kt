package com.alapshin.multiplayground.login.data

import me.tatarka.inject.annotations.Inject

@Inject
class LoginManagerImpl(val service: LoginService) : LoginManager {
    override suspend fun login(email: String, password: String): LoginResponse {
        return service.login(LoginRequest(email = email, password = password))
    }
}
