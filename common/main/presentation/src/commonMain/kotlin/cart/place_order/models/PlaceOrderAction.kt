package cart.place_order.models

sealed class PlaceOrderAction {
    object OnBackClick : PlaceOrderAction()
}