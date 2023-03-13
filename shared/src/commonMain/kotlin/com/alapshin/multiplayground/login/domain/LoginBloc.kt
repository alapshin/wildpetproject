package com.alapshin.multiplayground.login.domain

import kotlinx.coroutines.flow.Flow

interface LoginBloc {
    val state: Flow<LoginMvi.State>

    fun onLoginClicked(email: String, password: String)
}
