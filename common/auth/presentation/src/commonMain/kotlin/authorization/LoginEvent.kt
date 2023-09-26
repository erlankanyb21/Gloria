package authorization

sealed class LoginEvent {
    data class PhoneNumberChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    object PasswordShowClick : LoginEvent()
    object LoginClick : LoginEvent()
    object RegistrationClick : LoginEvent()
    object ForgotClick : LoginEvent()
}