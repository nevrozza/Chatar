package view

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

class ViewManager(
    var tint: MutableState<String> = mutableStateOf(ThemeTint.Auto.name),
    var isDynamic: MutableState<Boolean> = mutableStateOf(false),
    var isDark: MutableState<Boolean> = mutableStateOf(false),
    var size: BoxWithConstraintsScope? = null,
    var orientation: MutableState<WindowScreen> = mutableStateOf(WindowScreen.Vertical)
)
val LocalViewManager: ProvidableCompositionLocal<ViewManager> = compositionLocalOf {
    error("No ViewManager provided")
}