package settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import view.ThemeTint

class SettingsDataSource(
    private val settings: Settings
) {

    fun saveNew(new: Boolean) {
        settings[newKey] = new
    }

    fun fetchNew(): Boolean {
        return settings[newKey, true]
    }

    fun saveTint(tint: String) {
        settings[tintKey] = tint
    }

    fun fetchTint(): String {
        return settings[tintKey, ThemeTint.Auto.name]
    }

    fun saveIsDynamic(isDynamic: Boolean) {
        settings[isDynamicKey] = isDynamic
    }

    fun fetchIsDynamic(): Boolean? {
        return settings[isDynamicKey]
    }

    companion object {
        const val newKey = "newKey"
        const val tintKey = "tintKey"
        const val isDynamicKey = "dynamicKey"
    }

}