interface ChatRepository {
    fun fetchAllWords(): List<String>
    fun fetchDescription(word: String): String
}