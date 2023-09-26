package models.stories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null
)
