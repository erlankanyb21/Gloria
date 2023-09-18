package models.catalog

import kotlinx.serialization.Serializable

@Serializable
data class SubcategoriesItem(
    val image: String? = null,
    val name: String,
    val id: Int,
    val subcategorySlug: String? = null,
    val categories: Int
)