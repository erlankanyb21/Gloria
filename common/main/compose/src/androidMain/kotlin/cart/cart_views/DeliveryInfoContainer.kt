package cart.cart_views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import cart.place_order.models.PlaceOrderEvent
import cart.place_order.models.PlaceOrderViewState

@Composable
fun DeliveryInfoContainer(
    viewState: State<PlaceOrderViewState>,
    handleEvent: (PlaceOrderEvent) -> Unit
) {
    Column {
        CartTextField(
            value = viewState.value.deliveryInfo.fieldAddress,
            placeHolder = "Адрес"
        ) { text ->
            handleEvent(
                PlaceOrderEvent.DeliveryInfoChange(
                    PlaceOrderEvent.DeliveryInfoEvent.FieldAddressChange(text)
                )
            )
        }
        CartTextField(
            value = viewState.value.deliveryInfo.fieldApartmentNumber,
            placeHolder = "Квартира / Офис"
        ) { text ->
            handleEvent(
                PlaceOrderEvent.DeliveryInfoChange(
                    PlaceOrderEvent.DeliveryInfoEvent.FieldApartmentNumberChange(text)
                )
            )
        }

        CartTextField(
            value = viewState.value.deliveryInfo.fieldFloorAndDoorCode,
            placeHolder = "Этаж, код двери / домофона"
        ) { text ->
            handleEvent(
                PlaceOrderEvent.DeliveryInfoChange(
                    PlaceOrderEvent.DeliveryInfoEvent.FieldFloorAndDoorCodeChange(text)
                )
            )
        }

        CartTextField(
            value = viewState.value.deliveryInfo.fieldMoreInfo,
            placeHolder = "Дополнительно к заказу"
        ) { text ->
            handleEvent(
                PlaceOrderEvent.DeliveryInfoChange(
                    PlaceOrderEvent.DeliveryInfoEvent.FieldMoreInfoChange(text)
                )
            )
        }
    }
}