package models.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItems(
    val id: Int? = null,
    val price: Double? = null,
    @SerialName("product_slug")
    val productSlug: String? = null,
    val description: String? = null,
    @SerialName("is_hit")
    val isHit: Boolean? = null,
    val categories: Int? = null,
    val subcategories: Int? = null,
    @SerialName("total_price")
    val totalPrice: Double? = null,
    val quantity: Int? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    val product: Int? = null,
    val user: Int? = null,
    val balls: Int? = null,
    val postcard: Int? = null,
    val name: String? = null
)
