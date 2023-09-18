package models.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartItemImage(
    val id: Int,
    val image: String
)
