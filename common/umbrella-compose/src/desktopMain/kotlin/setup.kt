import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposePanel
import root.RootComponent
import view.WindowType
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun JFrame.init(root: RootComponent) {

    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    title = "gimnSaki admin"

    val composePanel = ComposePanel()
    composePanel.setContent {
        Root(root, WindowType.PC)
    }

    minimumSize = Dimension(360, 640)
    contentPane.add(composePanel, BorderLayout.CENTER)
    setSize(1300, 700)
    setLocationRelativeTo(null)
    isVisible = true
}

@Composable
actual fun StatusBarColorFix() {}