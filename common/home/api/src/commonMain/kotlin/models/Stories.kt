package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("created_at")
    val createdAt: String
)
