package home.models

sealed class HomeEvent {

    data class StoriesDetailsClick(val id: Int) : HomeEvent()
    data class StoriesDetailsDownload(val imageId: Int) : HomeEvent()
    object SalesHitsClick : HomeEvent()

    data class CartClick(val productId: Int) : HomeEvent()
    data class FavoriteClick(val productId: Int) : HomeEvent()
    data class InFavoriteClick(val productId: Int): HomeEvent()
    object ContactsAndAddressesClick : HomeEvent()
    object AnswersAndQuestionsClick : HomeEvent()
}
