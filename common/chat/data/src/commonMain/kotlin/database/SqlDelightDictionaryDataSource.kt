package database

import com.nevrozq.chatar.Database

class SqlDelightDictionaryDataSource(
    private val database: Database
) {

    fun fetchAllWords(): List<String> {
        return database.dictionariesQueries.getAllWords().executeAsList()
            .map {
                it.word ?: ""
            }
    }

    fun fetchDescription(word: String): String {
        return database.dictionariesQueries.getDescription(word).executeAsOne().description ?: ""
    }
}