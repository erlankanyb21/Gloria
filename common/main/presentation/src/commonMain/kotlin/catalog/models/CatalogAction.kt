package catalog.models

import cart.models.CartAction

sealed class CatalogAction {

    object OpenCatalog : CatalogAction()
}