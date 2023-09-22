import model.AccessTokenResponse
import model.RegisterResponse

interface AuthRepository {
    suspend fun login(phone_number: String, password: String): AccessTokenResponse
    fun isUserLoggedIn(): Boolean
    fun loadTokens(): Pair<String?, String?>

    suspend fun signUp(fullName: String,phoneNumber: String, password: String): RegisterResponse

    suspend fun confirmUser(code: String): String
}