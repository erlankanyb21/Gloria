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

    fun saveUserId(id: Int) {
        settings.putInt(userId, id)
    }

    fun setUserId(): Int {
        return settings[userId, 0]
    }

    companion object {
        private val refreshTokenKey = "refreshTokenKey"
        private val accessTokenKey = "accessTokenKey"
        private val userId = "userId"
    }
}