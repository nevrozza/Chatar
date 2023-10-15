package inFlow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import components.inFlow.BookInfoComponent
import components.inFlow.MainComponent
import components.inFlow.MainComponent.Model

class MainComponentImpl(
    componentContext: ComponentContext,
    val onBookClick: (BookInfoComponent.Model) -> Unit
): MainComponent, ComponentContext by componentContext {
    override val model: Value<Model> = MutableValue(Model())

    override fun onBookClicked(model: BookInfoComponent.Model) {
        onBookClick(model)
    }


}