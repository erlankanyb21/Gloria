package navigation

import NavigationTree
import numberConfirmation.NumberConfirmation
import passwordRecovery.CreatedNewPassword
import passwordRecovery.PasswordRecovery
import passwordRecovery.PasswordRecoverySms
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import signin.SignInView
import signup.SignUpScreen

fun RootComposeBuilder.authFlow() {
    flow(name = NavigationTree.Auth.AuthFlow.name) {
        screen(name = NavigationTree.Auth.SignIn.name) {
            SignInView().Content()
        }
        screen(name = NavigationTree.Auth.SignUp.name) {
            SignUpScreen()
        }
        screen(name = NavigationTree.Auth.NumberConfirmation.name) {
            NumberConfirmation()
        }
        screen(name = NavigationTree.Auth.CreatedNewPassword.name) {
            CreatedNewPassword()
        }
        screen(name = NavigationTree.Auth.PasswordRecovery.name) {
            PasswordRecovery()
        }
        screen(name = NavigationTree.Auth.PasswordRecoverySms.name) {
            PasswordRecoverySms()
        }
    }
}