package com.alapshin.multiplayground.login.data

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface LoginService {
    @POST("login/")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
