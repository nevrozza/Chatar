import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.ui.unit.Dp

@Composable
actual fun KeyboardHeight(): Dp {
    val density = LocalDensity.current
    val pxValue = WindowInsets.ime.getBottom(LocalDensity.current)
    return with(density) { pxValue.toDp() }
}