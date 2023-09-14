package models.more.faq

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FAQItems(
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
)