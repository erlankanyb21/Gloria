package models.token

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenBody(
    val refresh: String
)