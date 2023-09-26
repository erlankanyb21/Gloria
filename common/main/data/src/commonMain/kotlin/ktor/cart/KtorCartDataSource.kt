package ktor.cart

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.http.path
import models.cart.CartItems
import models.cart.ClearCartResponse
import settings.SettingsAuthDataSource

class KtorCartDataSource(
    private val httpClient: HttpClient,
    private val settingsAuthDataSource: SettingsAuthDataSource
) {

    suspend fun fetchUserCart(): List<CartItems> {
        return httpClient.get {
            url {
                path("cart-items/")
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }.body()
    }

    suspend fun clearCart(): ClearCartResponse? {
        return try {
            httpClient.delete {
                url {
                    path("cart-items/")
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
                path("cart-items/$productId/")
                bearerAuth(settingsAuthDataSource.fetchAccessToken())
            }
        }
    }
}