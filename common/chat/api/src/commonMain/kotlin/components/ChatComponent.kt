package components

import Message
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value



interface ChatComponent {
    val model: Value<Model>

    fun onMessageTextChange(text: String)
    fun sendMessage()

    fun clearMessages()

    fun onBackClicked()

    data class Model(
        val name: String = "",
        val messages: MutableList<Message> = mutableListOf(),
        val mText: String = ""
    )
}