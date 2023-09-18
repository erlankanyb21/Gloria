package repositories

import models.catalog.CatalogItem

interface CatalogRepository {

    suspend fun fetchCatalog(): CatalogItem
}