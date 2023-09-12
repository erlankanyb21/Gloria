package repositories

import ktor.KtorCartDataSource
import models.cart.CartResponse

class CartRepositoryImpl(
    private val remoteDataSource: KtorCartDataSource
): CartRepository {
    override suspend fun fetchUserCart(): CartResponse {
        return remoteDataSource.fetchUserCart()
    }
}