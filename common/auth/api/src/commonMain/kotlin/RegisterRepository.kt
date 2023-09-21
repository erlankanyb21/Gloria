import model.RegisterResponse

interface RegisterRepository {
    suspend fun register(fullname: String, phone_number: String, password: String): RegisterResponse
}

//    suspend fun login(phoneNumber: String, password: String): AccessTokenResponse
//    fun isUserLoggedIn(): Boolean
//    fun loadTokens(): Pair<String?, String?>