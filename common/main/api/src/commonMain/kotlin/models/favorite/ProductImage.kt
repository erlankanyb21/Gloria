package models.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductImage(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null
)
