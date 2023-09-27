package cart.cart.models

sealed class CartEvent{
    data class IncrementProductCount(val value: Int) : CartEvent()
    data class DecrementProductCount(val value: Int) : CartEvent()
    object ClearCart : CartEvent()
    data class RemoveItem(val productId: Int) : CartEvent()
    object OpenCatalogClick : CartEvent()
    object OnBackClick : CartEvent()
    object OpenPlaceOrder : CartEvent()
}