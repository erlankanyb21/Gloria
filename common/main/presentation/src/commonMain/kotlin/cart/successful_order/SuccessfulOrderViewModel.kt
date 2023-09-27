package cart.successful_order

import cart.successful_order.models.SuccessfulOrderAction
import cart.successful_order.models.SuccessfulOrderEvent
import cart.successful_order.models.SuccessfulOrderViewState
import com.adeo.kviewmodel.BaseSharedViewModel

class SuccessfulOrderViewModel :
    BaseSharedViewModel<SuccessfulOrderViewState, SuccessfulOrderAction, SuccessfulOrderEvent>(
        SuccessfulOrderViewState()
    ) {

    override fun obtainEvent(viewEvent: SuccessfulOrderEvent) {
        when(viewEvent) {
            SuccessfulOrderEvent.OnBackClick -> { onBackClick() }
            SuccessfulOrderEvent.OpenMainScreenClick -> { openMainScreen() }
        }
    }

    private fun openMainScreen() {
        viewAction = SuccessfulOrderAction.OpenMainScreen
    }

    private fun onBackClick() {
        viewAction = SuccessfulOrderAction.OnBackClick
    }
}