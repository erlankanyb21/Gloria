
object NavigationTree {
    enum class SplashScreen {
        SplashScreen
    }

    enum class Auth {
        AuthFlow,
        SignIn,
        SignUp,
        NumberConfirmation,
        CreatedNewPassword,
        PasswordRecovery,
        PasswordRecoverySms
    }

    enum class Main {
        MainFlow,
        MainScreen,
        Catalog,
        Cart,
        More,
        FAQ,
        ContactsAndAddress,
        StoriesDetails,
        Subcatalog,
        CatalogDetailScreen,
        PlaceOrder,
        SuccessfulOrder,
        Favorite
    }
}