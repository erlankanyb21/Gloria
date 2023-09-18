package ktor.cart

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.http.path
import models.cart.CartItems
import models.cart.ClearCartResponse

class KtorCartDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchUserCart(): List<CartItems> {
        return httpClient.get {
            url {
                path("cart-items/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MDQ1NDU0LCJpYXQiOjE2OTUwMzgyNTQsImp0aSI6ImRiZmVkZDdhYjg2ZDQyYjE4OWZmMzQ2OTJhODBlZGJjIiwidXNlcl9pZCI6MX0.4oBdAQiFecMv5aLVrbHOEB7cg9iVLson9YnoAhfF61A")
            }
        }.body()
    }

    suspend fun clearCart(): ClearCartResponse? {
        return try {
            httpClient.delete {
                url {
                    path("cart-items/")
                    bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MDQ1NDU0LCJpYXQiOjE2OTUwMzgyNTQsImp0aSI6ImRiZmVkZDdhYjg2ZDQyYjE4OWZmMzQ2OTJhODBlZGJjIiwidXNlcl9pZCI6MX0.4oBdAQiFecMv5aLVrbHOEB7cg9iVLson9YnoAhfF61A")
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
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MDQ1NDU0LCJpYXQiOjE2OTUwMzgyNTQsImp0aSI6ImRiZmVkZDdhYjg2ZDQyYjE4OWZmMzQ2OTJhODBlZGJjIiwidXNlcl9pZCI6MX0.4oBdAQiFecMv5aLVrbHOEB7cg9iVLson9YnoAhfF61A")
            }
        }
    }
}