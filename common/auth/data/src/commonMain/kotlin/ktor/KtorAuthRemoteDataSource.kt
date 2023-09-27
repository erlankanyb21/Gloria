package ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import model.AccessTokenResponse
import model.RegisterResponse
import model.signUp.ConfirmCode
import model.signUp.SignUp

class KtorAuthRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun executeLoginRequest(request: KtorLoginRequest): AccessTokenResponse {
        return httpClient.post {
            url {
                path("login/")
                setBody(request)
            }
        }.body()
    }

    suspend fun signUp(fullName: String, phoneNumber: String, password: String): RegisterResponse {
        return httpClient.post {
            url {
                path("register/")
                setBody(SignUp(fullName, phoneNumber, password))
            }
        }.body()
    }

    suspend fun confirmUser(code: String): String {
        return httpClient.post {
            url {
                path("confirm-user/")
                setBody(ConfirmCode(code))
            }
        }.body()
    }
}