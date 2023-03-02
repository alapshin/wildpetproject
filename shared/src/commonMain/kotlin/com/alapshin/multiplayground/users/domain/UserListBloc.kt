package com.alapshin.multiplayground.users.domain

import kotlinx.coroutines.flow.Flow

interface UserListBloc {
    val state: Flow<UserListMvi.State>
}
