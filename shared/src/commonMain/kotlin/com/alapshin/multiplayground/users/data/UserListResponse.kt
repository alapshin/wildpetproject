package com.alapshin.multiplayground.users.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponse(
    @SerialName("data")
    val users: List<User>,
)
