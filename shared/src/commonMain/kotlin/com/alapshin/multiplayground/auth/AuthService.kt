package com.alapshin.multiplayground.auth

import com.alapshin.multiplayground.login.data.Credentials
import com.alapshin.multiplayground.login.data.LoginResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface AuthService {
    @POST("login/")
    suspend fun login(@Body request: Credentials): LoginResponse
}
