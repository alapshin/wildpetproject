package com.alapshin.multiplayground.users.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alapshin.multiplayground.users.data.User
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun UserItemView(user: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val avatarUrl = user.avatar
        KamelImage(
            resource = lazyPainterResource(data = avatarUrl),
            contentDescription = "",
            modifier = Modifier.size(72.dp),
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text("${user.firstName} ${user.lastName}")
    }
}
