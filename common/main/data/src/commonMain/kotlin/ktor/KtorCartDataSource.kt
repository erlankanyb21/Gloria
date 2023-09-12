package ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.path
import models.cart.CartResponse

class KtorCartDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchUserCart(): CartResponse {
        return httpClient.get {
            url {
                path("cart-items")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk0NTIyOTE5LCJpYXQiOjE2OTQ1MDc2NTMsImp0aSI6IjZkYmJlMjJkNmNmNjRkZTg4NjkwMDZhNDllZjBlYjcxIiwidXNlcl9pZCI6OX0.SRwl_eUAfTVNb33WhxACnzzPH47DweQQ1tRUdg02Yno")
            }
        }.body()
    }
}