package cart.models

sealed class CartAction {
    object OpenCatalog : CartAction()
}