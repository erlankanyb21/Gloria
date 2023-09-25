package cart.cart.models

import models.cart.CartItems

data class CartViewState(
    val loading: Boolean = true,
    val cartItems: List<CartItems> = emptyList()
)