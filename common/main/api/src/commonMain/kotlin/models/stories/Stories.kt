package models.stories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    val id: Int? = null,
    val image: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null
)
