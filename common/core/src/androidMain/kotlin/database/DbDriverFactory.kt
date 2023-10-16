package database

import PlatformConfiguration
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.nevrozq.chatar.android.R
import java.io.FileOutputStream
import java.io.InputStream

actual class DbDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
//    actual fun createDriver(
//        schema: SqlSchema<QueryResult.Value<Unit>>,
//        name: String
//    ): SqlDriver = AndroidSqliteDriver(schema, platformConfiguration.androidContext, name)
    actual fun createDriver(
        schema: SqlSchema<QueryResult.Value<Unit>>,
        name: String
    ): SqlDriver {

    val context = platformConfiguration.androidContext

    val existingDatabase = context.getDatabasePath(name)

    if (existingDatabase.exists()) {
        // If the database file already exists, delete it
        existingDatabase.delete()
    }

    val inputStream = context.resources.openRawResource(R.raw.dictionaries)
    val outputStream = FileOutputStream(existingDatabase)

    inputStream.use { input: InputStream ->
        outputStream.use { output: FileOutputStream ->
            input.copyTo(output)
        }
    }

    return AndroidSqliteDriver(schema, context, name)
    }
}