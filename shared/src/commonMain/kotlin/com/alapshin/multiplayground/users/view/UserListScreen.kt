package com.alapshin.multiplayground.users.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alapshin.multiplayground.image.AsyncImage
import com.alapshin.multiplayground.image.asyncPainterResource
import com.alapshin.multiplayground.users.domain.UserListBloc
import com.alapshin.multiplayground.users.domain.UserListMvi

@Composable
fun UserListScreen(bloc: UserListBloc) {
    val state by bloc.state.collectAsState(UserListMvi.State())
    state.users?.let { users ->
        Column {
            for (user in users) {
                Row {
                    AsyncImage(
                        resource = asyncPainterResource(data = "https://picsum.photos/id/237/200/300"),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(user.username)
                }
            }
        }
    }
}
