package com.alapshin.multiplayground.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Long,
    val username: String,
)
