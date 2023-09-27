package cart.place_order

import cart.place_order.models.PlaceOrderAction
import cart.place_order.models.PlaceOrderEvent
import cart.place_order.models.PlaceOrderViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.cart.CartRepository

class PlaceOrderViewModel :
    BaseSharedViewModel<PlaceOrderViewState, PlaceOrderAction, PlaceOrderEvent>(
        PlaceOrderViewState()
    ) {

    private val cartRepository: CartRepository = Inject.instance()
    override fun obtainEvent(viewEvent: PlaceOrderEvent) {
        when(viewEvent) {
            is PlaceOrderEvent.OnBackClick -> {
                onBackClick()
            }
            is PlaceOrderEvent.FieldDateOfOrderChanged -> {
                viewState = viewState.copy(fieldDateOfOrder = viewEvent.value)
            }
            is PlaceOrderEvent.FieldNameChanged -> {
                viewState = viewState.copy(fieldName = viewEvent.value)
            }
            is PlaceOrderEvent.FieldPhoneNumberChanged -> {
                viewState = viewState.copy(fieldPhone = viewEvent.value)
            }
            is PlaceOrderEvent.SetSwitchState -> {
                viewState = viewState.copy(switchState = viewEvent.value)
            }
            is PlaceOrderEvent.SelectFilial -> {
                viewState = viewState.copy(selectedFilial = viewEvent.value)
            }
            is PlaceOrderEvent.SelectRadioButton -> {
                viewState.radioButtonsState
                viewState = viewState.copy(
                    radioButtonsState = viewState.radioButtonsState.map {
                        it.copy(isChecked = it.text == viewEvent.value)
                    }
                )
            }
            is PlaceOrderEvent.DeliveryInfoChange -> {
                when(viewEvent.value) {
                    is PlaceOrderEvent.DeliveryInfoEvent.FieldAddressChange -> {
                        viewState = viewState.copy(
                            deliveryInfo = viewState.deliveryInfo.copy(
                                fieldAddress = viewEvent.value.value
                            )
                        )
                    }
                    is PlaceOrderEvent.DeliveryInfoEvent.FieldApartmentNumberChange -> {
                        viewState = viewState.copy(
                            deliveryInfo = viewState.deliveryInfo.copy(
                                fieldApartmentNumber = viewEvent.value.value
                            )
                        )
                    }
                    is PlaceOrderEvent.DeliveryInfoEvent.FieldMoreInfoChange -> {
                        viewState = viewState.copy(
                            deliveryInfo = viewState.deliveryInfo.copy(
                                fieldMoreInfo = viewEvent.value.value
                            )
                        )
                    }
                    is PlaceOrderEvent.DeliveryInfoEvent.FieldFloorAndDoorCodeChange -> {
                        viewState = viewState.copy(
                            deliveryInfo = viewState.deliveryInfo.copy(
                                fieldFloorAndDoorCode = viewEvent.value.value
                            )
                        )
                    }
                }
            }
        }
    }

    fun fetchUserCart() {
        viewModelScope.launch {
            viewState = try {
                val result = cartRepository.fetchUserCart()
                var totalValues = 0.0
                result.forEach { item->
                    val res = item.price * item.quantity
                    totalValues += res
                }
                viewState.copy(
                    cartItems = result,
                    totalValue = totalValues
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    cartItems = emptyList()
                )
            }
        }
    }

    fun getFilialList() {
        viewModelScope.launch {
            viewState = try {
                viewState.copy(
                    filialList = cartRepository.getFilial(),
                    selectedFilial = cartRepository.getFilial().firstOrNull()?.nameAddress ?: "",
                    cartItems = viewState.cartItems,
                    totalValue = viewState.totalValue
                )
            }catch (e: Exception) {
                e.printStackTrace()
                viewState.copy()
            }
        }
    }
    private fun onBackClick() {
        viewAction = PlaceOrderAction.OnBackClick
    }
}