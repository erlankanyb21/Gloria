package models.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItems(
    val id: Int,
    @SerialName("product_images")
    val productImages: List<CartItemImage>,
    val price: Double,
    @SerialName("product_slug")
    val productSlug: String? = null,
    val description: String? = null,
    @SerialName("is_hit")
    val isHit: Boolean? = null,
    val categories: Int? = null,
    val subcategories: Int? = null,
    @SerialName("total_price")
    val totalPrice: Double? = null,
    var quantity: Int = 1,
    @SerialName("created_at")
    val createdAt: String? = null,
    val order: Int? = null,
    val product: Int,
    val user: Int,
    val name: String? = null
)
