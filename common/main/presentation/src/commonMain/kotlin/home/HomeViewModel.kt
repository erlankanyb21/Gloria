package home

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import home.models.HomeAction
import home.models.HomeEvent
import home.models.HomeViewState
import repositories.HomeRepository

class HomeViewModel : BaseSharedViewModel<HomeViewState, HomeAction, HomeEvent>(
    HomeViewState(storiesDetails = "")
) {
    private val homeRepository: HomeRepository = Inject.instance()

    override fun obtainEvent(viewEvent: HomeEvent) {
        when (viewEvent) {
            is HomeEvent.StoriesDetailsClick -> showStoriesDetails(viewEvent.id)
            is HomeEvent.SalesHitsClick -> showSalesHits()
            is HomeEvent.ContactsAndAddressesClick -> showContactsAndAddresses()
            is HomeEvent.AnswersAndQuestionsClick -> showFAQ()
        }
    }

    private fun showStoriesDetails(id: Int) {
        withViewModelScope {
            val response = homeRepository.fetchStoriesDetails(id)
            if (response.image != null) {
                val image = response.image!!.replace("http://", "https://")
                viewState = viewState.copy(storiesDetails = image)
                viewAction = HomeAction.OpenStoriesDetails
            }
        }
    }

    private fun showSalesHits() {
        viewAction = HomeAction.OpenSalesHits
    }

    private fun showContactsAndAddresses() {
        viewAction = HomeAction.OpenContactsAndAddresses
    }

    private fun showFAQ() {
        viewAction = HomeAction.OpenFAQ
    }

    init {
        withViewModelScope {
            viewState = viewState.copy(
                stories = homeRepository.fetchStories(),
                advertisingBanner = homeRepository.fetchAdvertisingBanner(),
            )
        }
    }
}