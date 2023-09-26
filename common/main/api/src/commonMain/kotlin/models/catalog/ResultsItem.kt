package models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsItem(
    @SerialName("product_slug")
    val productSlug: String? = null,
    @SerialName("product_images")
    val productImages: List<ProductImagesItem>?,
    @SerialName("is_hit")
    val isHit: Boolean = false,
    val price: String,
    val name: String,
    val id: Int? = null,
    val categories: CatalogItem,
)