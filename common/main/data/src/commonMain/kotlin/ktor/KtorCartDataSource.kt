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
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk0NjExMDI2LCJpYXQiOjE2OTQ2MDM4MjYsImp0aSI6IjkxNDliMDJhZWY2ZTQ4ZmU4NWJmNWU1ZjdiZTI4ZTc5IiwidXNlcl9pZCI6MX0.prsW13Fj-DMydbb83Txaa8oNVpXsqE3K06t9z7FXSKQ")
            }
        }.body()
    }
}