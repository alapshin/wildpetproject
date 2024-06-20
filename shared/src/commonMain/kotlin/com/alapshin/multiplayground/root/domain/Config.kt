package com.alapshin.multiplayground.root.domain

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {
    @Serializable
    data object Landing : Config
    @Serializable
    data object Login : Config
    @Serializable
    data object Registration : Config
    @Serializable
    data object UserList : Config
}
