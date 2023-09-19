package ktor

import kotlinx.serialization.Serializable

@Serializable
data class KtorRegisterRequest (
    val fullname: String,
    val phone_number: String,
    val password: String
)