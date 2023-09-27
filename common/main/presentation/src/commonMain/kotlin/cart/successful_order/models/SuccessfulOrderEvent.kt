package cart.successful_order.models

sealed class SuccessfulOrderEvent {
    object OnBackClick: SuccessfulOrderEvent()
    object OpenMainScreenClick: SuccessfulOrderEvent()
}