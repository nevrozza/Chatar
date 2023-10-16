data class Message(
    val text: String,
    val hourMinutes: String,
    val day: String,
    val isBot: Boolean,
    val words: List<String> = listOf(),
    val index: Int = 0
)
