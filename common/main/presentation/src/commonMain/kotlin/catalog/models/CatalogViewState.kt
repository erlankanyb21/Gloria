package catalog.models

import models.catalog.CatalogItem
import models.catalog.ProductResponse
import models.catalog.ResultsItem

data class CatalogViewState(
    val catalogItem: List<CatalogItem> = emptyList(),
    val subCatalogItem: CatalogItem? = null,
    val productItem : ProductResponse? = null,
    val loading : Boolean = true
)