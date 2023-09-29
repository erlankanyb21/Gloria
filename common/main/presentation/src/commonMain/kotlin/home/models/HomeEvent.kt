package home.models

sealed class HomeEvent {

    data class StoriesDetailsClick(val id: Int): HomeEvent()
    data class StoriesDetailsDownload(val imageId: Int): HomeEvent()
    object SalesHitsClick: HomeEvent()
    object ContactsAndAddressesClick: HomeEvent()
    object AnswersAndQuestionsClick: HomeEvent()
}
