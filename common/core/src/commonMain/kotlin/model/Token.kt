package model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val refresh: String,
    val access: String,
)
