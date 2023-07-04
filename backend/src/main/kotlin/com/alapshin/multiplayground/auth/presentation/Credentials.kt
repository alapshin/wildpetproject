package com.alapshin.multiplayground.auth.presentation

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val username: String,
    val password: String,
)
