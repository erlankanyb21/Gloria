package cart.place_order.models

sealed class PlaceOrderEvent {
    object OnBackClick : PlaceOrderEvent()
    data class FieldNameChanged(val value: String) : PlaceOrderEvent()
    data class FieldPhoneNumberChanged(val value: String) : PlaceOrderEvent()
    data class FieldDateOfOrderChanged(val value: String) : PlaceOrderEvent()
    data class SetSwitchState(val value: Boolean) : PlaceOrderEvent()
    data class SelectFilial(val value: String) : PlaceOrderEvent()
    data class SelectRadioButton(val value: String) : PlaceOrderEvent()
    data class DeliveryInfoChange(val value: DeliveryInfoEvent) : PlaceOrderEvent()

    sealed class DeliveryInfoEvent {
        data class FieldAddressChange(val value: String): DeliveryInfoEvent()
        data class FieldApartmentNumberChange(val value: String): DeliveryInfoEvent()
        data class FieldFloorAndDoorCodeChange(val value: String): DeliveryInfoEvent()
        data class FieldMoreInfoChange(val value: String): DeliveryInfoEvent()
    }
}
