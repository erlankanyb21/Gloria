package catalog.models

import cart.cart.models.CartAction

sealed class CatalogAction {

    object OpenCatalog : CatalogAction()
}