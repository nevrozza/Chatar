import android.content.res.Resources.Theme
import android.view.ViewManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import root.RootComponent
import view.LocalViewManager
import view.ThemeTint

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun ComponentActivity.init(root: RootComponent) {
    setContent {
        Root(root)
    }
}

@Composable
actual fun StatusBarColorFix() {
    val viewManager = LocalViewManager.current
    val systemUiController = rememberSystemUiController()
    if(!isSystemInDarkTheme()) {

            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = viewManager.tint.value != ThemeTint.Dark.name
            )

    }
}