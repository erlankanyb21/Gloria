package repositories

import models.cart.CartResponse

interface CartRepository {

    suspend fun fetchUserCart(): CartResponse
}