package view

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(colorScheme: ColorScheme, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun colorSchemeGetter(viewManager: ViewManager): ColorScheme {
    return if (viewManager.isDynamic.value) {
        if (viewManager.isDark.value) {
            dynamicDarkScheme()!!
        } else {
            dynamicLightScheme()!!
        }
    } else {
        if ((viewManager.tint.value == ThemeTint.Auto.name && viewManager.isDark.value)
            || viewManager.tint.value == ThemeTint.Dark.name) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    }
}