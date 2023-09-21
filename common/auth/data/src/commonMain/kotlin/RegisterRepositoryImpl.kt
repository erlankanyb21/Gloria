import ktor.KtorAuthRemoteDataSource
import ktor.KtorRegisterRequest
import model.RegisterResponse

class RegisterRepositoryImpl(
    private val remoteDataSource: KtorAuthRemoteDataSource
): RegisterRepository {
    override suspend fun register(fullname: String, phone_number: String, password: String): RegisterResponse {
        val register  = remoteDataSource.executeRegisterRequest(
            request = KtorRegisterRequest(
                fullname = fullname,
                phone_number = phone_number,
                password = password
            )
        )
        return register
    }

}