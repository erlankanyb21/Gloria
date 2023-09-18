package repositories

import models.catalog.CatalogItem
import models.catalog.SubcategoriesItem

interface CatalogRepository {

    suspend fun fetchCatalog(): List<CatalogItem>
    suspend fun fetchSubCatalog(slug: String): CatalogItem
}