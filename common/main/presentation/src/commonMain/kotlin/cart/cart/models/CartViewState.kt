package cart.cart.models

import models.cart.CartItems

data class CartViewState(
    val loading: Boolean = true,
    val progress: Boolean = false,
    val cartItems: List<CartItems> = emptyList()
)