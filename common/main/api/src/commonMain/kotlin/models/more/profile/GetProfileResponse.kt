package models.more.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileResponse(
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("date_of_birthday")
    val dateOfBirthday: String? = null,
    @SerialName("fullname")
    val fullname: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null
)