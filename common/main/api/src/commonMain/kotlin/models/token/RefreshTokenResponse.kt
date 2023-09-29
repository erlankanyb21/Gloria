package models.token

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val access: String
)