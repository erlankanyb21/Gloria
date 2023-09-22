package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("user_id")
    val userId: Int
)
