package ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import model.AccessTokenResponse
import model.RegisterResponse

class KtorAuthRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun executeLoginRequest(request: KtorLoginRequest): AccessTokenResponse{
        return httpClient.post{
            url{
                path("/v1/login/")
                setBody(request)
            }
        }.body()
    }

    suspend fun executeRegisterRequest(request: KtorRegisterRequest): RegisterResponse {
        return httpClient.post {
            url {
                path("/v1/register/")
                setBody(request)
            }
        }.body()
    }
}