package model

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResponse(
    val id: Int,
    val detail: TokenDetail
)
