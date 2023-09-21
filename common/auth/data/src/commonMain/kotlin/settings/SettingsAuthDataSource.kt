package settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SettingsAuthDataSource(private val settings: Settings) {
    fun saveTokens(refreshToken: String, accessToken: String) {
        settings.putString(refreshTokenKey, refreshToken)
        settings.putString(accessTokenKey, accessToken)
    }

    fun fetchRefreshToken(): String {
        return settings[refreshTokenKey, ""]
    }

    fun fetchAccessToken(): String {
        return settings[accessTokenKey, ""]
    }

    companion object {
        private val refreshTokenKey = "refreshTokenKey"
        private val accessTokenKey = "accessTokenKey"
    }
}