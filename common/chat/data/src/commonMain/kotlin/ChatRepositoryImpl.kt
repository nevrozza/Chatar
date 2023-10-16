import database.SqlDelightDictionaryDataSource

class ChatRepositoryImpl(
    private val localDataSource: SqlDelightDictionaryDataSource
): ChatRepository {
    override fun fetchAllWords(): List<String> {
        return localDataSource.fetchAllWords()
    }

    override fun fetchDescription(word: String): String {
        return localDataSource.fetchDescription(word)
    }
}