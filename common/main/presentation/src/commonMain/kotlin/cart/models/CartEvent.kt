package cart.models

sealed class CartEvent {
    object ClearCart : CartEvent()
    object RemoveItem : CartEvent()
    object PlusProduct : CartEvent()
    object MinusProduct : CartEvent()
}