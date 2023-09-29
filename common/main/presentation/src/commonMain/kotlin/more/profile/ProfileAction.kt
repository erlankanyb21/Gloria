package more.profile

sealed class ProfileAction {
    object OpenFAQ : ProfileAction()
    object OpenQA : ProfileAction()

    object OpenFavorite: ProfileAction()
}