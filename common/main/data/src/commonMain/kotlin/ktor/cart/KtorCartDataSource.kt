package ktor.cart

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import io.ktor.http.path
import kotlinx.serialization.Serializable
import models.cart.CartItems
import models.cart.ClearCartResponse
import models.cart.FilialModel
import models.cart.SetProductQuantityModel
import models.token.RefreshTokenBody
import settings.SettingsAuthDataSource

class KtorCartDataSource(
    private val httpClient: HttpClient,
    private val settingsAuthDataSource: SettingsAuthDataSource
) {

    suspend fun fetchUserCart(): List<CartItems> {
        val response = httpClient.get {
            url {
                path(CART_ITEMS)
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }
        if (response.status == HttpStatusCode.Unauthorized) {
            val tokenRefresh = httpClient.post {
                setBody(RefreshTokenBody(settingsAuthDataSource.fetchRefreshToken()))
                url(TOKEN_REFRESH)
            }
            if (tokenRefresh.status == HttpStatusCode.Unauthorized) {
                val login = httpClient.post {
                    setBody(
                        LoginBody(
                        "+996502020000",
                        "argen1234")
                    )
                    url(LOGIN)
                }.body<LoginResponse>()
                settingsAuthDataSource.saveTokens(
                    refreshToken = login.refresh,
                    accessToken = login.access
                )
                return httpClient.get {
                    url {
                        path(CART_ITEMS)
                        bearerAuth(settingsAuthDataSource.fetchAccessToken())
                    }
                }.body()
            }
            settingsAuthDataSource.saveTokens(
                settingsAuthDataSource.fetchRefreshToken(),
                tokenRefresh.body<RefreshTokenBody>().refresh
            )

            return httpClient.get {
                url {
                    path(CART_ITEMS)
                    bearerAuth(settingsAuthDataSource.fetchAccessToken())
                }
            }.body()
        }
        return response.body()
    }

    suspend fun clearCart(): ClearCartResponse? {
        return try {
            httpClient.delete {
                url {
                    path(CART_ITEMS)
                    bearerAuth(settingsAuthDataSource.fetchAccessToken())
                }
            }.body()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun deleteCartItem(productId: Int) {
        httpClient.delete {
            url {
                path("$CART_ITEMS$productId")
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }
    }

    suspend fun setProductQuantity(quantity: Int, body: SetProductQuantityModel): CartItems {
        return httpClient.patch {
            setBody(body)
            url {
                path("$CART_ITEMS$quantity")
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }.body()
    }

    suspend fun getFilial(): List<FilialModel> {
        return httpClient.get {
            setBody(body)
            url {
                path(FILIAL)
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }.body()
    }

    companion object {
        const val FILIAL = "filial/"
        const val CART_ITEMS = "cart-items/"
        const val LOGIN = "login/"
        const val TOKEN_REFRESH = "token/refresh/"
    }
}

@Serializable
data class LoginBody(
    val phone_number: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val refresh: String,
    val access: String
)