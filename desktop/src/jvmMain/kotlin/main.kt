import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.alapshin.multiplayground.App

fun main() = application {
    Window(
        title = "Compose for Desktop",
        state = rememberWindowState(width = 640.dp, height = 480.dp),
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}

