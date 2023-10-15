interface SettingsRepository {
    fun saveNew(new: Boolean)
    fun fetchNew(): Boolean

    fun saveTint(tint: String)
    fun fetchTint(): String

    fun saveIsDynamic(isDynamic: Boolean)
    fun fetchIsDynamic(): Boolean?
}