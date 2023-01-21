package com.example.multiplayground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform