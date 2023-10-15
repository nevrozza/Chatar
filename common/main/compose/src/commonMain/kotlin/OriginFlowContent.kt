import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import animations.horizontalSlide
import animations.iosLikeSlide
import animations.slideEnterModifier
import animations.slideExitModifier
import animations.verticalSlide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.getValue
import com.arkivanov.essenty.backhandler.BackHandler
import components.OriginFlowComponent
import components.OriginFlowComponent.Child.BookInfoChild
import components.OriginFlowComponent.Child.ChatChild
import components.OriginFlowComponent.Child.MainChild
import components.inFlow.BookInfoComponent
import inFlow.BookInfoContent
import inFlow.MainContent


@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun OriginFlowContent(component: OriginFlowComponent) {

    Children(
        stack = component.childStack,
        animation = stackAnimation(slide()),
        ) {
        when (val child = it.instance) {
            is MainChild -> MainContent(child.component)
            is BookInfoChild -> BookInfoContent(child.component)
            is ChatChild -> ChatContent(child.component)
        }
    }
}