package catalog.models

sealed class CatalogEvent {
    data class OpenSubCatalogClick(val slag: String) : CatalogEvent()

    object OpenProductClick: CatalogEvent()

    object OnBackClick : CatalogEvent()
}