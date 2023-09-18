package repositories

import ktor.cart.KtorCartDataSource
import models.cart.CartItems
import models.cart.ClearCartResponse

class CartRepositoryImpl(
    private val remoteDataSource: KtorCartDataSource
): CartRepository {
    override suspend fun fetchUserCart(): List<CartItems> {
        return remoteDataSource.fetchUserCart()
    }

    override suspend fun clearCart(): ClearCartResponse? {
        return remoteDataSource.clearCart()
    }

    override suspend fun deleteCartItem(productId: Int) {
        remoteDataSource.deleteCartItem(productId)
    }
}