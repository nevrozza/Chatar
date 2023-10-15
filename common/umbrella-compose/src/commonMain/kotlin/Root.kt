import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import di.Inject
import root.RootComponent
import view.AppTheme
import view.LocalViewManager
import view.ThemeTint
import view.ViewManager
import view.WindowCalculator
import view.WindowType
import view.colorSchemeGetter
import view.dynamicDarkScheme
import view.dynamicLightScheme
import view.isCanInDynamic

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun Root(root: RootComponent, device: WindowType = WindowType.Phone) {
    val settingsRepository: SettingsRepository = Inject.instance()
    tintInit(settingsRepository)
    dynamicInit(settingsRepository)
    val viewManager = remember {
        ViewManager(
            tint = mutableStateOf(settingsRepository.fetchTint()),
            isDynamic = mutableStateOf(settingsRepository.fetchIsDynamic()!!)
        )
    }

    viewManager.isDark.value = if (viewManager.tint.value == ThemeTint.Auto.name) isSystemInDarkTheme()
        else viewManager.tint.value  == ThemeTint.Dark.name

    val colorScheme = colorSchemeGetter(viewManager)
//        if (viewManager.isDynamic.value) {
//            if (viewManager.isDark.value) {
//                dynamicDarkScheme()!!
//            } else {
//                dynamicLightScheme()!!
//            }
//        }
//        else if(themeManager.color.value == ThemeColors.Dynamic.name && !isCanInDynamic()) {
//            schemeChooser(themeManager.isDark.value, ThemeColors.Default.name)
//        }
//        else {
//            schemeChooser(themeManager.isDark.value, themeManager.color.value)
//        }

    BoxWithConstraints() {
        viewManager.size = this
        viewManager.orientation.value =
            WindowCalculator.calculateScreen(size = DpSize(this.maxWidth, this.maxHeight), device)

        CompositionLocalProvider(
            LocalViewManager provides viewManager
        ) {
            AppTheme(colorScheme = colorScheme) {
                StatusBarColorFix()
                RootContent(root)
            }
        }
    }
}

fun tintInit(settingsRepository: SettingsRepository): String {
    return when {
        settingsRepository.fetchTint().isBlank() -> {
            settingsRepository.saveTint(ThemeTint.Auto.name)
            ThemeTint.Auto.name
        }
        else -> {
            settingsRepository.fetchTint()
        }
    }
}

fun dynamicInit(settingsRepository: SettingsRepository): Boolean {
    val isDynamic = settingsRepository.fetchIsDynamic()
    return if (isCanInDynamic()) {
        if(isDynamic != null) {
            isDynamic
        } else {
            settingsRepository.saveIsDynamic(true)
            true
        }
    } else {
        if (isDynamic == null)
            settingsRepository.saveIsDynamic(false)
        false
    }
}

@Composable
expect fun StatusBarColorFix()
