import model.AccessTokenResponse

interface AuthRepository {
    suspend fun login(phone_number: String, password: String): AccessTokenResponse
    fun isUserLoggedIn(): Boolean
    fun loadTokens(): Pair<String?, String?>
}