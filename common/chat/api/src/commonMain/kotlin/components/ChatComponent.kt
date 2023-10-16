package components

import Message
import com.arkivanov.decompose.value.Value



interface ChatComponent {
    val model: Value<Model>

    fun onMessageTextChange(text: String)
    fun onWordClicked(word: String)
    fun sendMessage()

    fun clearMessages()
    fun costilF()

    fun onBackClicked()

    data class Model(
        val title: String = "",
        val dbName: String = "",
        val messages: MutableList<Message> = mutableListOf(),
        val mText: String = "",
        val costil: Boolean = false
    )
}