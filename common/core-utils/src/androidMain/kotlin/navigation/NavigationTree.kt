package navigation

object NavigationTree {
    enum class SplashScreen{
        SplashScreen
    }

    enum class Auth{
        SignIn, SignUp, NumberConfirmation, CreatedNewPassword, PasswordRecovery, PasswordRecoverySms
    }

    enum class Main{
        MainScreen, Tab
    }
}