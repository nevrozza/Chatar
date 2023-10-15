
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import components.ChatComponent
import components.ChatComponent.Model
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime

class ChatComponentImpl(
    componentContext: ComponentContext,
    val onBackClick: () -> Unit
): ChatComponent, ComponentContext by componentContext {
    private val _models = MutableValue(Model())
    override val model: Value<Model> = _models
    override fun onMessageTextChange(text: String) {
        _models.value = _models.value.copy(mText = text)
    }

    override fun sendMessage() {
        val lTime = Clock.System.now().toLocalDateTime(
            currentSystemDefault()
        )
        val tempMessagesList = model.value.messages
        println(model.value.messages.size.toString())

        _models.value = _models.value.copy(messages = tempMessagesList.plus(Message(model.value.mText, getTime(lTime), getDayMonth(lTime), false, index = model.value.messages.size)).toMutableList()
        )
        onMessageTextChange("")
    }

    override fun clearMessages() {
        _models.value = _models.value.copy(messages = mutableListOf())
    }

    override fun onBackClicked() {
        onBackClick()
    }


}

fun getTime(lTime: LocalDateTime): String {
    val hour = if(lTime.hour.toString().length == 1) "0"+lTime.hour.toString() else lTime.hour.toString()
    val minutes = if(lTime.minute.toString().length == 1) "0"+lTime.minute.toString() else lTime.minute.toString()
    return "${hour}:${minutes}"
}

fun getDayMonth(lTime: LocalDateTime): String {
    val month = when(lTime.monthNumber) {
        1 -> "янв."
        2 -> "фев."
        3 -> "мар."
        4 -> "апр"
        5 -> "мая."
        6 -> "июн."
        7 -> "июл."
        8 -> "авг."
        9 -> "сент."
        10 -> "окт."
        11 -> "гоя."
        else -> "дек."
    }
    val dayOfMonth = if(lTime.dayOfMonth.toString().length == 1) "0"+lTime.dayOfMonth.toString() else lTime.dayOfMonth.toString()
    return "$dayOfMonth $month"
}