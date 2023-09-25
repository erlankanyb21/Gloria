package repositories.cart

import models.cart.CartItems
import models.cart.ClearCartResponse
import models.cart.FilialModel
import models.cart.SetProductQuantityModel

interface CartRepository {

    suspend fun fetchUserCart(): List<CartItems>
    suspend fun clearCart(): ClearCartResponse?
    suspend fun deleteCartItem(productId: Int)
    suspend fun setProductQuantity(quantity: Int, body: SetProductQuantityModel): CartItems
    suspend fun getFilial(): List<FilialModel>
}