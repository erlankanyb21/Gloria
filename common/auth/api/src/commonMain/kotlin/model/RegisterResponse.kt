package model

data class RegisterResponse(
    val message: String,
    val code: String,
    val user_id: Int
)
