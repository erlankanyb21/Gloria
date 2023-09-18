package models.advertisingBanner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdvertisingBanner(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("link")
    val link: String
)
