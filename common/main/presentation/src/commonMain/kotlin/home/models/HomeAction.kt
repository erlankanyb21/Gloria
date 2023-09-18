package home.models

sealed class HomeAction {

    object OpenStoriesDetails : HomeAction()
    object OpenSalesHits : HomeAction()
    object OpenContactsAndAddresses : HomeAction()
    object OpenFAQ : HomeAction()
}