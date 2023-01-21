package com.example.multiplayground

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        MessageCard(Message("Android", "Hello"))
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    Row {
//        Image(
//            painter =
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = message.author)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message.body)
        }
    }
}
