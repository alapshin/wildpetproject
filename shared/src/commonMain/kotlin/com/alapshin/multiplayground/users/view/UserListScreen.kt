package com.alapshin.multiplayground.users.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alapshin.multiplayground.users.domain.UserListBloc
import com.alapshin.multiplayground.users.domain.UserListMvi

@Composable
fun UserListScreen(bloc: UserListBloc) {
    val state by bloc.state.collectAsState(UserListMvi.State())
    state.users?.let { users ->
        Column {
            for (user in users) {
                Text(user.username)
            }
        }
    }
}
