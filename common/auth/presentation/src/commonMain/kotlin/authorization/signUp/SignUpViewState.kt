package authorization.signUp
data class SignUpViewState (
    val fullName: String,
    val phoneNumber: String,
    val password: String,
    val passwordConfirm: String,
    val isSending: Boolean = false,
    val passwordHidden: Boolean = true,
    val passwordConfirmHidden: Boolean = true
)
