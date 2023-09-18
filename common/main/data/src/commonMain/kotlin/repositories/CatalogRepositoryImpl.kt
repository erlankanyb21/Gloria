package repositories

import ktor.catalog.CatalogDataSource
import models.catalog.CatalogItem
import models.catalog.SubcategoriesItem

class CatalogRepositoryImpl(
    private val remoteDataSource: CatalogDataSource
) : CatalogRepository {
    override suspend fun fetchCatalog(): List<CatalogItem> {
        return remoteDataSource.fetchCatalog()
    }

    override suspend fun fetchSubCatalog(slug: String): CatalogItem {
        return remoteDataSource.fetchSubCatalog(slug)
    }
}