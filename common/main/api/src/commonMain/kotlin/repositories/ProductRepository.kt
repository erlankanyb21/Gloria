package repositories

import models.catalog.ProductResponse


interface ProductRepository {

    suspend fun fetchProduct(): ProductResponse
}