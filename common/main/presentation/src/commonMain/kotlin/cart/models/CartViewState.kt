package cart.models

import models.cart.CartItems

data class CartViewState(
    val productCount: Int,
    val isEmptyCart: Boolean,
    val cartItems: List<CartItems> = emptyList()
)