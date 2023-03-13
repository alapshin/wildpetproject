package com.alapshin.multiplayground.root.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed interface Config : Parcelable {
    object Login : Config
    object UserList : Config
}
