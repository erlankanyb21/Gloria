package catalog.models

sealed class CatalogAction {

    data class OpenSubCatalog(val slag: String) : CatalogAction()

    object OnBackClick : CatalogAction()

    object OpenProduct : CatalogAction()
}