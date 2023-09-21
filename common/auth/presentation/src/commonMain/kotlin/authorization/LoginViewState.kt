package authorization

data class LoginViewState(
    val phone_number: String,
    val password: String,
    val isSending: Boolean = false,
    val passwordHidden: Boolean = true
)