package base

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import ktor.cart.KtorCartDataSource
import models.token.RefreshTokenBody
import models.token.RefreshTokenResponse
import models.token.RefreshTokensRequest
import models.token.RefreshTokensResponse
import settings.SettingsAuthDataSource

open class BaseKtorDataSource(
    private val httpClient: HttpClient,
    private val settingsAuthDataSource: SettingsAuthDataSource
) {
    suspend inline fun <reified T> makeRequestWithToken(call: () -> HttpResponse): T {
        val response = call()
        if (response.status == HttpStatusCode.Unauthorized) {
            handleUnauthorized()
        }
        return response.body()
    }

    suspend fun handleUnauthorized() {
        val tokenRefresh = httpClient.post {
            setBody(RefreshTokenBody(settingsAuthDataSource.fetchRefreshToken()))
            url(KtorCartDataSource.TOKEN_REFRESH)
        }

        if (tokenRefresh.status == HttpStatusCode.Unauthorized) {
            val login = httpClient.post {
                setBody(
                    RefreshTokensRequest(
                        settingsAuthDataSource.fetchUserNumber(),
                        settingsAuthDataSource.fetchUserPassword()
                    )
                )
                url(KtorCartDataSource.REFRESH_TOKENS)
            }.body<RefreshTokensResponse>()

            settingsAuthDataSource.saveTokens(
                refreshToken = login.refresh,
                accessToken = login.access
            )
        } else {
            settingsAuthDataSource.saveAccessToken(tokenRefresh.body<RefreshTokenResponse>().access)
        }
    }
}