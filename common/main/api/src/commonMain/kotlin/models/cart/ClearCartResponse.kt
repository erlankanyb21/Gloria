package models.cart

import kotlinx.serialization.Serializable

@Serializable
data class ClearCartResponse(
    val message: String
)