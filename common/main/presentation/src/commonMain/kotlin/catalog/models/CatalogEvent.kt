package catalog.models

sealed class CatalogEvent {
    data class OpenSubCatalogClick(val slag: String) : CatalogEvent()

    object OpenProductClick: CatalogEvent()

    data class CartClick(val productId: Int): CatalogEvent()

    object OnBackClick : CatalogEvent()
}