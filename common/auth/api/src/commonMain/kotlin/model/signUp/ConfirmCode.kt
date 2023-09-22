package model.signUp

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmCode(
    val code: String
)
