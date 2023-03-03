import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

const val WINDOW_WIDTH = 600
const val WINDOW_HEIGHT = 480

fun main() = application {
    Window(
        title = "Compose for Desktop",
        state = rememberWindowState(width = WINDOW_WIDTH.dp, height = WINDOW_HEIGHT.dp),
        onCloseRequest = ::exitApplication,
    ) {
    }
}
