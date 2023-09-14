package cart.models

import models.cart.CartItems

data class CartViewState(
    val isNotEmptyCart: Boolean = false,
    val cartItems: List<CartItems> = emptyList()
)