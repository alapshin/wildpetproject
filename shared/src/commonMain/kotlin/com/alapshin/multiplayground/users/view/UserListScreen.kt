package com.alapshin.multiplayground.users.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alapshin.multiplayground.users.domain.UserListBloc
import com.alapshin.multiplayground.users.domain.UserListMvi

@Composable
fun UserListScreen(bloc: UserListBloc) {
    val state by bloc.state.collectAsState(UserListMvi.State())
    state.users?.let { users ->
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                users,
                key = { user -> user.id}
            ) {user ->
                UserItemView(user)
            }
        }
    }
}
