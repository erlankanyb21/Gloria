package settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SettingsAuthDataSource(private val settings: Settings) {
    fun saveTokens(refreshToken: String, accessToken: String) {
        settings.putString(refreshTokenKey, refreshToken)
        settings.putString(accessTokenKey, accessToken)
    }

    fun saveAccessToken(accessToken: String) {
        settings.putString(accessTokenKey, accessToken)
    }

    fun saveUserData(number: String, password: String) {
        settings.putString(USER_NUMBER, number)
        settings.putString(USER_PASSWORD, password)
    }

    fun fetchUserNumber(): String {
        return settings[USER_NUMBER, ""]
    }

    fun fetchUserPassword(): String {
        return settings[USER_PASSWORD, ""]
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

    fun clearSettings() {
        settings.clear()
    }

    companion object {
        private const val refreshTokenKey = "refreshTokenKey"
        private const val accessTokenKey = "accessTokenKey"
        private const val userId = "userId"
        private const val USER_NUMBER = "userNumber"
        private const val USER_PASSWORD = "userPassword"
    }
}