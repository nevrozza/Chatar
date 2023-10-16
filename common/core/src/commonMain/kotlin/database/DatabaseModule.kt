package database

import com.nevrozq.chatar.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import kotlin.math.sin

internal val databaseModule = DI.Module("databaseModule") {
    bind<DbDriverFactory>() with singleton {
        DbDriverFactory(instance())

    }
    bind<Database>() with singleton  {
        val driverFactory = instance<DbDriverFactory>()
        val driver = driverFactory.createDriver(Database.Schema, "dictionaries.db")

        Database(driver)
    }
}