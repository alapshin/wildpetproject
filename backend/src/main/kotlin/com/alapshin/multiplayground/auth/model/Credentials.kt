package com.alapshin.multiplayground.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val username: String,
    val password: String,
)
