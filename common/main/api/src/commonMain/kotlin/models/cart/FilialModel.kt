package models.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilialModel(
    val id: Int,
    @SerialName("name_address")
    val nameAddress: String
)