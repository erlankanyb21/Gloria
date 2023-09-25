package cart.place_order.models

import models.cart.CartItems
import models.cart.FilialModel

data class PlaceOrderViewState(
    val fieldName: String = "",
    val fieldPhone: String = "",
    val fieldDateOfOrder: String = "",
    val switchState: Boolean = false,
    val filialList: List<FilialModel> = emptyList(),
    val selectedFilial: String = filialList.firstOrNull()?.nameAddress ?: "",
    val radioButtonsState: List<RadioButtonInfo> = listOf(
        RadioButtonInfo("Самовывоз", true),
        RadioButtonInfo("Доставка", false)
    ),
    val deliveryInfo: DeliveryInfo = DeliveryInfo(),
    val totalValue: Double = 0.0,
    val cartItems: List<CartItems> = emptyList()
) {
    data class DeliveryInfo(
        val fieldAddress: String = "",
        val fieldApartmentNumber: String = "",
        val fieldFloorAndDoorCode: String = "",
        val fieldMoreInfo: String = ""
    )
}