package authorization.signUp

sealed class SignUpAction {
    object OpenMainFlow: SignUpAction()
    object OpenSignIn: SignUpAction()
}
