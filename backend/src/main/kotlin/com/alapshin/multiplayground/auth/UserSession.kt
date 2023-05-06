package com.alapshin.multiplayground.auth

import io.ktor.server.auth.Principal

data class UserSession(
    val username: String
) : Principal
