package repositories

import models.cart.CartItems
import models.cart.ClearCartResponse

interface CartRepository {

    suspend fun fetchUserCart(): List<CartItems>
    suspend fun clearCart(): ClearCartResponse?
    suspend fun deleteCartItem(productId: Int)
}