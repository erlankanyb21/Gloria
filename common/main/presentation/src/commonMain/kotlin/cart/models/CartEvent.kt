package cart.models

sealed class CartEvent {
    data class IncrementProductCount(val value: Int) : CartEvent()
    data class DecrementProductCount(val value: Int) : CartEvent()
    object ClearCart : CartEvent()
    object RemoveItem : CartEvent()
    object OpenCatalogClick : CartEvent()
}