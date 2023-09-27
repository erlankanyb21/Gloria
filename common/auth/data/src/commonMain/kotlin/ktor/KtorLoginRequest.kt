package ktor

import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginRequest (
    val phone_number: String,
    val password: String
)

