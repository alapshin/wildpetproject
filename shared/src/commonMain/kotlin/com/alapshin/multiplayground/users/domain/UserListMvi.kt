package com.alapshin.multiplayground.users.domain

import com.alapshin.multiplayground.users.data.User

class UserListMvi private constructor() {
    data class State(
        val error: Error? = null,
        val progress: Boolean = false,
        val users: List<User>? = null,
    )

    sealed interface Error

    sealed interface Event {
        object Load : Event
    }
}
