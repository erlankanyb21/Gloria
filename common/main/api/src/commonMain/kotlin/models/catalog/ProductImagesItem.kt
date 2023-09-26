package models.catalog

import kotlinx.serialization.Serializable

@Serializable
data class ProductImagesItem(
    val image: String? = null,
    val id: Int? = null
)