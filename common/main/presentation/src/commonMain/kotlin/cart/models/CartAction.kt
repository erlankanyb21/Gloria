package cart.models

sealed class CartAction {
    object OpenCatalog : CartAction()
    object OnBackClick : CartAction()
}