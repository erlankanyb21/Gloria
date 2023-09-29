package ktor.cart

import base.BaseKtorDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.path
import models.cart.CartItems
import models.cart.ClearCartResponse
import models.cart.FilialModel
import models.cart.SetProductQuantityModel
import settings.SettingsAuthDataSource

class KtorCartDataSource(
    private val httpClient: HttpClient,
    private val settingsAuthDataSource: SettingsAuthDataSource
) : BaseKtorDataSource(httpClient, settingsAuthDataSource) {

    suspend fun fetchUserCart(): List<CartItems> = makeRequestWithToken {
        httpClient.get {
            url {
                path(CART_ITEMS)
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }
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
        makeRequestWithToken<Unit> {
            httpClient.delete {
                url {
                    path("$CART_ITEMS$productId/")
                    bearerAuth(settingsAuthDataSource.fetchAccessToken())
                }
            }
        }
    }

    suspend fun setProductQuantity(quantity: Int, body: SetProductQuantityModel): CartItems {
        return makeRequestWithToken {
            httpClient.patch {
                setBody(body)
                url {
                    path("$CART_ITEMS$quantity/")
                    bearerAuth(settingsAuthDataSource.fetchAccessToken())
                }
            }
        }
    }

    suspend fun getFilial(): List<FilialModel> {
        return httpClient.get {
            setBody(body)
            url {
                path(FILIAL)
            }
        }.body()
    }

    companion object {
        const val FILIAL = "filial/"
        const val CART_ITEMS = "cart-items/"
        const val REFRESH_TOKENS = "token/"
        const val TOKEN_REFRESH = "token/refresh/"
    }
}