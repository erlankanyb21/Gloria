package authorization.signUp

sealed class SignUpEvent {
    object PasswordShowClick : SignUpEvent()
    object PasswordConfirmShowClick : SignUpEvent()
    object RegistrationClick : SignUpEvent()
    data class FullNameChanged(val fullName: String) : SignUpEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class PasswordConfirmChanged(val passwordChanged: String) : SignUpEvent()
}
