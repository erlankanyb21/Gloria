package models.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartBody(
    val quantity: Int,
    @SerialName("product")
    val productId: Int,
    @SerialName("user")
    val userId: Int
)
