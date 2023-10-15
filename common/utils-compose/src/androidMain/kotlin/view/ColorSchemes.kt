package view

import PlatformConfiguration
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import di.Inject

@Composable
actual fun dynamicDarkScheme(): ColorScheme? {
    val platformConfiguration: PlatformConfiguration = Inject.instance()
    return dynamicDarkColorScheme(platformConfiguration.androidContext)
}

@Composable
actual fun dynamicLightScheme(): ColorScheme? {
    val platformConfiguration: PlatformConfiguration = Inject.instance()
    return dynamicLightColorScheme(platformConfiguration.androidContext)
}

actual fun isCanInDynamic(): Boolean  = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S