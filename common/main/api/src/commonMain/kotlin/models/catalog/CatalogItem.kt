package models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogItem(
    val subcategories: List<SubcategoriesItem>?,
    val image: String? = null,
    val name: String,
    val id: Int,
    @SerialName("category_slug")
    val categorySlug: String
)