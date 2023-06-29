package com.alapshin.multiplayground.auth.model

import io.ktor.server.auth.Principal

data class UserSession(
    val username: String,
) : Principal
