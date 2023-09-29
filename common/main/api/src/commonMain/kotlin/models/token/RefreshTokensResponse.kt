package models.token

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokensResponse(
    val refresh: String,
    val access: String
)