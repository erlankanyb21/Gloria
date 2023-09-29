package model

import kotlinx.serialization.Serializable

@Serializable
data class TokenRefresh(
    val refresh: String,
)
