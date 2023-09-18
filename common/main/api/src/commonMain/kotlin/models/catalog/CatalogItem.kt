package models.catalog

import kotlinx.serialization.Serializable

@Serializable
data class CatalogItem(
    val subcategories: List<SubcategoriesItem>?,
    val image: String,
    val name: String,
    val id: Int,
    val categorySlug: String
)