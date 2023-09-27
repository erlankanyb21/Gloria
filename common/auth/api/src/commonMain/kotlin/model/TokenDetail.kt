package model

import kotlinx.serialization.Serializable

@Serializable
data class TokenDetail(
    val refresh: String,
    val access: String
)
