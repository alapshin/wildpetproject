package com.alapshin.multiplayground.users.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String
)
