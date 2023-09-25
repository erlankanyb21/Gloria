package repositories.cart

import ktor.cart.KtorCartDataSource
import models.cart.CartItems
import models.cart.ClearCartResponse
import models.cart.FilialModel
import models.cart.SetProductQuantityModel

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

    override suspend fun setProductQuantity(quantity: Int, body: SetProductQuantityModel): CartItems {
        return remoteDataSource.setProductQuantity(quantity, body)
    }

    override suspend fun getFilial(): List<FilialModel> {
        return remoteDataSource.getFilial()
    }
}