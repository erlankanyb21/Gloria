package models.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokensRequest(
    @SerialName("phone_number")
    val phoneNumber: String,
    val password: String
)