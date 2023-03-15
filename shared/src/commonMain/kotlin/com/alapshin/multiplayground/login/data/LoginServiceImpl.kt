package com.alapshin.multiplayground.login.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import me.tatarka.inject.annotations.Inject

const val ENDPOINT_LOGIN = "login/"

@Inject
class LoginServiceImpl(private val httpClient: HttpClient) : LoginService {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return httpClient.post(ENDPOINT_LOGIN) {
            setBody(request)
        }.body()
    }
}
