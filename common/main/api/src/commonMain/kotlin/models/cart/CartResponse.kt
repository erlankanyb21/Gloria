package models.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartResponse(
    @SerialName("cart_items")
    val cartItems: List<CartItems> = listOf()
)