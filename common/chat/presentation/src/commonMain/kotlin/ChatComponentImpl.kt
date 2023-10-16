import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import components.ChatComponent
import components.ChatComponent.Model
import di.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime


class ChatComponentImpl(
    componentContext: ComponentContext,
    private val chatModel: Model,
    val onBackClick: () -> Unit
) : ChatComponent, ComponentContext by componentContext {
    private val _models = MutableValue(chatModel)
    override val model: Value<Model> = _models
    private val chatRepository: ChatRepository = Inject.instance()
    override fun onMessageTextChange(text: String) {
        _models.value = _models.value.copy(mText = text)
    }

    override fun onWordClicked(word: String) {

        val lTime = Clock.System.now().toLocalDateTime(
            currentSystemDefault()
        )
        GlobalScope.launch(Dispatchers.IO) {
            _models.value.messages.add(
                Message(
                    "$word\n${chatRepository.fetchDescription(word)}",
                    getTime(lTime),
                    getDayMonth(lTime),
                    true,
                    index = model.value.messages.size
                )
            )
            costilF()
            delay(100)
            costilF()
        }
        costilF()
    }

    override fun sendMessage() {
        val lTime = Clock.System.now().toLocalDateTime(
            currentSystemDefault()
        )
        _models.value.messages.add(
            Message(
                model.value.mText,
                getTime(lTime),
                getDayMonth(lTime),
                false,
                index = model.value.messages.size
            )
        )
        val text = _models.value.mText

        onMessageTextChange("")
        BotMessage(text)



    }

    fun BotMessage(text: String) {
        val lTime = Clock.System.now().toLocalDateTime(
            currentSystemDefault()
        )

        GlobalScope.launch(Dispatchers.IO) {
            val listOfWords = findClosestMatches(text, chatRepository.fetchAllWords(), 2)

            if (listOfWords.isNotEmpty()) {
                _models.value.messages.add(
                    Message(
                        "Вот, что мы нашли:",
                        getTime(lTime),
                        getDayMonth(lTime),
                        true,
                        listOfWords,
                        index = model.value.messages.size
                    )
                )


            } else {
                _models.value.messages.add(
                    Message(
                        "Мы ничего не нашли(",
                        getTime(lTime),
                        getDayMonth(lTime),
                        true,
                        index = model.value.messages.size
                    )
                )
            }
            costilF()
            delay(100)
            costilF()
        }
        costilF()
    }

    fun findClosestMatches(query: String, wordList: List<String>, maxDistance: Int): List<String> {
        val result = mutableListOf<String>()
        val queryLower = query.lowercase()

        for (word in wordList) {
            val wordLower = word.lowercase()
            val distance = levenshteinDistance(queryLower, wordLower)

            if (distance <= maxDistance) {
                result.add(word)
            }
        }

        // Если нет совпадений, возвращаем пустой список
        if (result.isEmpty()) {
            return emptyList()
        }

        // Сортируем результаты по расстоянию
        result.sortBy { levenshteinDistance(queryLower, it.toLowerCase()) }

        // Возвращаем максимум 4 ближайших совпадения, включая слово с нулевым расстоянием
        return result.subList(0, minOf(result.size, 4))
    }

    override fun clearMessages() {
        _models.value = _models.value.copy(messages = mutableListOf())
    }

    override fun costilF() {
        _models.value = _models.value.copy(costil = !_models.value.costil)
    }

    override fun onBackClicked() {
        onBackClick()
    }


}

fun getTime(lTime: LocalDateTime): String {
    val hour =
        if (lTime.hour.toString().length == 1) "0" + lTime.hour.toString() else lTime.hour.toString()
    val minutes =
        if (lTime.minute.toString().length == 1) "0" + lTime.minute.toString() else lTime.minute.toString()
    return "${hour}:${minutes}"
}

fun getDayMonth(lTime: LocalDateTime): String {
    val month = when (lTime.monthNumber) {
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
    val dayOfMonth =
        if (lTime.dayOfMonth.toString().length == 1) "0" + lTime.dayOfMonth.toString() else lTime.dayOfMonth.toString()
    return "$dayOfMonth $month"
}

fun levenshteinDistance(s1: String, s2: String): Int {
    val m = s1.length
    val n = s2.length

    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 0..m) {
        for (j in 0..n) {
            if (i == 0) {
                dp[i][j] = j
            } else if (j == 0) {
                dp[i][j] = i
            } else if (s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
            }
        }
    }

    return dp[m][n]
}