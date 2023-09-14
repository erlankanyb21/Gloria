package repositories

import ktor.catalog.CatalogDataSource
import models.catalog.CatalogItem

class CatalogRepositoryImpl(
    private val remoteDataSource: CatalogDataSource
) : CatalogRepository {
    override suspend fun fetchCatalog(): CatalogItem {
        return remoteDataSource.fetchCatalog()
    }
}