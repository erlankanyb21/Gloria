package home.models

sealed class HomeAction {

    data class OpenStoriesDetails(val id: Int) : HomeAction()
    object OpenSalesHits : HomeAction()
    object OpenContactsAndAddresses : HomeAction()
    object OpenFAQ : HomeAction()
}