import database.databaseModule
import org.kodein.di.DI
import settings.settingsInjectModule

val coreModule = DI.Module("coreModule") {

    importAll(
        databaseModule,
        settingsInjectModule
    )
}