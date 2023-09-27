package models.cart

import kotlinx.serialization.Serializable

@Serializable
data class SetProductQuantityModel(
    val quantity: Int,
    val product: Int,
    val user: Int
)
