package components

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import components.inFlow.BookInfoComponent
//import components.inFlow.NewComponent
import components.inFlow.MainComponent


interface OriginFlowComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class MainChild(val component: MainComponent) : Child()
        class BookInfoChild(val component: BookInfoComponent) : Child()
        class ChatChild(val component: ChatComponent) : Child()
    }
}