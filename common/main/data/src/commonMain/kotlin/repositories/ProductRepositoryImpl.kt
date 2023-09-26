package repositories

import ktor.product.KtorProductDataSource
import models.catalog.ProductResponse

class ProductRepositoryImpl(
    private val remoteDataSource: KtorProductDataSource
) : ProductRepository {

    override suspend fun fetchProduct(): ProductResponse {
        return remoteDataSource.fetchProduct()
    }
}