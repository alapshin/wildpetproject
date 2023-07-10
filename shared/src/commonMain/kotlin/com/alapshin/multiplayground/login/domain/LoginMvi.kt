package com.alapshin.multiplayground.login.domain

class LoginMvi private constructor() {
    data class State(
        val error: Error? = null,
        val progress: Boolean = false,
        val success: Boolean? = null,
    )

    sealed interface Error

    sealed interface Event {
        data class Login(
            val email: String,
            val password: String,
        ) : Event
    }
}
