package cart.successful_order.models

sealed class SuccessfulOrderAction {
    object OnBackClick : SuccessfulOrderAction()
    object OpenMainScreen : SuccessfulOrderAction()
}