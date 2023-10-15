package components.inFlow

import com.arkivanov.decompose.value.Value

interface MainComponent {
    val model: Value<Model>

    //fun onExitClicked()

    data class Model(
        val title: String = "",
        val text: String = ""
    )

    fun onBookClicked(model: BookInfoComponent.Model)
}