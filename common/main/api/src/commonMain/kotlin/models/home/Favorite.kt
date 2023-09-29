package models.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.catalog.ProductImagesItem

@Serializable
data class Favorite(
    val id: Int? = null,
    @SerialName("product_images")
    val productImage: List<ProductImagesItem>? = null,
    val user: Int,
    val product: Int,
    val name: String? = null,
    val price: String? = null,
    @SerialName("product_slug")
    val productSlug: String? = null,
    val description: String? = null,
    @SerialName("is_hit")
    val isHit: Boolean = false,

    )
