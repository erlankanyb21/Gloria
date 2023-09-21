package authorization

sealed class LoginAction {
    object OpenMainFlow : LoginAction()
    object OpenRegistrationScreen : LoginAction()

    object OpenForgotPasswordScreen : LoginAction()
}