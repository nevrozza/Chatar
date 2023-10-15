import settings.SettingsDataSource

class SettingsRepositoryImpl(
    private val cacheDataSource: SettingsDataSource
): SettingsRepository {
    override fun saveNew(new: Boolean) {
        cacheDataSource.saveNew(new)
    }

    override fun fetchNew(): Boolean {
        return cacheDataSource.fetchNew()
    }

    override fun saveTint(tint: String) {
        cacheDataSource.saveTint(tint)
    }

    override fun fetchTint(): String {
        return cacheDataSource.fetchTint()
    }

    override fun saveIsDynamic(isDynamic: Boolean) {
        cacheDataSource.saveIsDynamic(isDynamic)
    }

    override fun fetchIsDynamic(): Boolean? {
        return cacheDataSource.fetchIsDynamic()
    }

}