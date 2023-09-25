package ktor.cart

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

class KtorCartDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchUserCart(): List<CartItems> {
        return httpClient.get {
            url {
                path("cart-items/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1Mzg5NTc4LCJpYXQiOjE2OTUzODIzNzgsImp0aSI6IjI2MTg4NGE3ZmU4MzQ4MDE4YWNmYzg4ZDE4ZjQyNGZiIiwidXNlcl9pZCI6MX0.IlJNW6KevKYTy1Q_2bIp5uLvaD7sr04jkfnV_V7ySa4")
            }
        }.body()
    }

    suspend fun clearCart(): ClearCartResponse? {
        return try {
            httpClient.delete {
                url {
                    path("cart-items/")
                    bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1Mzg5NTc4LCJpYXQiOjE2OTUzODIzNzgsImp0aSI6IjI2MTg4NGE3ZmU4MzQ4MDE4YWNmYzg4ZDE4ZjQyNGZiIiwidXNlcl9pZCI6MX0.IlJNW6KevKYTy1Q_2bIp5uLvaD7sr04jkfnV_V7ySa4")
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
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1Mzg5NTc4LCJpYXQiOjE2OTUzODIzNzgsImp0aSI6IjI2MTg4NGE3ZmU4MzQ4MDE4YWNmYzg4ZDE4ZjQyNGZiIiwidXNlcl9pZCI6MX0.IlJNW6KevKYTy1Q_2bIp5uLvaD7sr04jkfnV_V7ySa4")
            }
        }
    }

    suspend fun setProductQuantity(quantity: Int, body: SetProductQuantityModel): CartItems {
        return httpClient.patch {
            setBody(body)
            url {
                path("cart-items/$quantity/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1Mzg5NTc4LCJpYXQiOjE2OTUzODIzNzgsImp0aSI6IjI2MTg4NGE3ZmU4MzQ4MDE4YWNmYzg4ZDE4ZjQyNGZiIiwidXNlcl9pZCI6MX0.IlJNW6KevKYTy1Q_2bIp5uLvaD7sr04jkfnV_V7ySa4")
            }
        }.body()
    }

    suspend fun getFilial(): List<FilialModel> {
        return httpClient.get {
            setBody(body)
            url {
                path("filial/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1Mzg5NTc4LCJpYXQiOjE2OTUzODIzNzgsImp0aSI6IjI2MTg4NGE3ZmU4MzQ4MDE4YWNmYzg4ZDE4ZjQyNGZiIiwidXNlcl9pZCI6MX0.IlJNW6KevKYTy1Q_2bIp5uLvaD7sr04jkfnV_V7ySa4")
            }
        }.body()
    }
}