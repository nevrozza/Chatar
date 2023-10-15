import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children

@Composable
actual fun getOjegov(): Painter {
    return painterResource("ojegov.jpg")
}