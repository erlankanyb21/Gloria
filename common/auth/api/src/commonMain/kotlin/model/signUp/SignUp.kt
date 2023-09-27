package model.signUp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUp(
    @SerialName("fullname")
    val fullName: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val password: String
)
