package com.alapshin.multiplayground.users.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Long,
    val username: String,
)
