package repositories.cart

import ktor.cart.KtorCartDataSource
import models.cart.CartResponse
import repositories.cart.CartRepository

class CartRepositoryImpl(
    private val remoteDataSource: KtorCartDataSource
): CartRepository {
    override suspend fun fetchUserCart(): CartResponse {
        return remoteDataSource.fetchUserCart()
    }
}