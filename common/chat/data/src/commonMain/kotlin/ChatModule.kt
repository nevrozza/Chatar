import database.SqlDelightDictionaryDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val chatModule = DI.Module("chatModule") {
    bind<SqlDelightDictionaryDataSource>() with provider {
        SqlDelightDictionaryDataSource(instance())
    }
    bind<ChatRepository>() with singleton {
        ChatRepositoryImpl(instance())
    }
}