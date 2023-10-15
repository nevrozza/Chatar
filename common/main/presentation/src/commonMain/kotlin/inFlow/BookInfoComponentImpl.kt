package inFlow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import components.inFlow.BookInfoComponent
import components.inFlow.BookInfoComponent.Model

class BookInfoComponentImpl(
    componentContext: ComponentContext,
    private val bookInfoModel: Model,
    val onChatClick: () -> Unit,
    val onBackClick: () -> Unit
): BookInfoComponent, ComponentContext by componentContext {
    private val _models = MutableValue(bookInfoModel)
    override val model: Value<Model> = _models

    override fun onChatClicked() {
        onChatClick()
    }

    override fun onBackClicked() {
        onBackClick()
    }
}