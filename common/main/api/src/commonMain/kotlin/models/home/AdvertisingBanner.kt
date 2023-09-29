package models.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdvertisingBanner(
    val id: Int,
    @SerialName("category_name")
    val categoryName: String,
    val image: String,
    @SerialName("created_at")
    val createdAt: String,
    val category: Int,
)
