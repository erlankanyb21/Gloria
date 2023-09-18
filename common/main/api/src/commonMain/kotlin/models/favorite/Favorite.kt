package models.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("product_images")
    val productImages: ProductImage? = null,
    @SerialName("user")
    val user: Int? = null,
    @SerialName("product")
    val product: Int? = null
)
