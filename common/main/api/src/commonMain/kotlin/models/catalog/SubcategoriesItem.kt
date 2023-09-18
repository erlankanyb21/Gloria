package models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubcategoriesItem(
    val image: String? = null,
    val name: String,
    val id: Int,
    @SerialName("subcategory_slug")
    val subcategorySlug: String? = null,
    val categories: Int
)