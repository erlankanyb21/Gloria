package home

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import home.models.HomeAction
import home.models.HomeEvent
import home.models.HomeViewState
import repositories.HomeRepository

class HomeViewModel : BaseSharedViewModel<HomeViewState, HomeAction, HomeEvent>(
    HomeViewState()
) {
    private val homeRepository: HomeRepository = Inject.instance()

    override fun obtainEvent(viewEvent: HomeEvent) {
        when (viewEvent) {
            is HomeEvent.StoriesDetailsClick -> showStoriesDetails()
            is HomeEvent.SalesHitsClick -> showSalesHits()
            is HomeEvent.ContactsAndAddressesClick -> showContactsAndAddresses()
            is HomeEvent.AnswersAndQuestionsClick -> showFAQ()
        }
    }

    private fun showStoriesDetails() {
        viewAction = HomeAction.OpenStoriesDetails
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