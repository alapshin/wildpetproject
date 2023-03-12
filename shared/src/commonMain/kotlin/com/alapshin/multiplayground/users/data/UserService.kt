package com.alapshin.multiplayground.users.data

import de.jensklingenberg.ktorfit.http.GET

interface UserService {
    @GET("users/")
    suspend fun getUsers(): UserListResponse
}
