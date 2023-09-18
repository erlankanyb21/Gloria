package catalog.models

import models.catalog.CatalogItem

data class CatalogViewState(
    val catalogItem: List<CatalogItem> = emptyList(),
    val subCatalogItem: CatalogItem? = null
)