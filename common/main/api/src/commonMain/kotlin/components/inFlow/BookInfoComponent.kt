package components.inFlow

import com.arkivanov.decompose.value.Value

interface BookInfoComponent {
    val model: Value<Model>

    fun onBackClicked()

    data class Model(
        val dbName: String,
        val title: String,
        val author: String,
        val date: String,
        val point: String,
        val description: String = "",
        val titlePrev: String = ""
    )

    fun onChatClicked()
}