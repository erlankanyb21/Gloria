package repositories

import ktor.catalog.KtorCatalogDataSource
import models.catalog.CatalogItem

class CatalogRepositoryImpl(
    private val remoteDataSource: KtorCatalogDataSource
) : CatalogRepository {
    override suspend fun fetchCatalog(): CatalogItem {
        return remoteDataSource.fetchCatalog()
    }
}