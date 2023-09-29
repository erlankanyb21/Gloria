import ktor.KtorAuthRemoteDataSource
import ktor.KtorLoginRequest
import model.AccessTokenResponse
import model.RegisterResponse
import settings.SettingsAuthDataSource

class AuthRepositoryImpl(
    private val remoteDataSource: KtorAuthRemoteDataSource,
    private val settingsAuthDataSource: SettingsAuthDataSource
) : AuthRepository {
    override suspend fun login(phone_number: String, password: String): AccessTokenResponse {
        val token = remoteDataSource.executeLoginRequest(
            request = KtorLoginRequest(
                phone_number = phone_number,
                password = password
            )
        )
        settingsAuthDataSource.saveUserId(token.id)
        settingsAuthDataSource.saveTokens(token.detail.refresh, token.detail.access)
        settingsAuthDataSource.saveUserData(phone_number, password)
        return token
    }

    override fun isUserLoggedIn(): Boolean {
        val accessToken = settingsAuthDataSource.fetchAccessToken()
        if (accessToken.isNotEmpty()) {
            println(accessToken)
        } else {
            println("token not $accessToken")
        }

        return accessToken.isNotEmpty()
    }

    override fun loadTokens(): Pair<String?, String?> {
        val refreshToken = settingsAuthDataSource.fetchRefreshToken()
        val accessToken = settingsAuthDataSource.fetchAccessToken()
        return Pair(refreshToken, accessToken)
    }

    override suspend fun signUp(
        fullName: String,
        phoneNumber: String,
        password: String
    ): RegisterResponse {
        val response = remoteDataSource.signUp(fullName, phoneNumber, password)
        settingsAuthDataSource.saveUserId(response.userId)
        return response
    }

    override suspend fun confirmUser(code: String): String {
        return remoteDataSource.confirmUser(code)
    }
}