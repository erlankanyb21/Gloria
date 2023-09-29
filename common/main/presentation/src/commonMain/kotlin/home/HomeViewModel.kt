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
            is HomeEvent.CartClick -> addCartProduct(viewEvent.productId)
            is HomeEvent.ContactsAndAddressesClick -> showContactsAndAddresses()
            is HomeEvent.AnswersAndQuestionsClick -> showFAQ()
            is HomeEvent.StoriesDetailsDownload -> downloadStoriesDetails(viewEvent.imageId)
        }
    }

    private fun addCartProduct(productId: Int) {
        withViewModelScope {
            homeRepository.fetchAddCart(productId)
        }
    }

    private fun downloadStoriesDetails(imageId: Int) {
        withViewModelScope {
            val image = homeRepository.fetchStoriesDetails(imageId).image
            if (image.toString().isNotBlank()) {
                viewState = viewState.copy(storiesDetails = image.toString())
            }
        }
    }

    private fun showStoriesDetails(id: Int) {
        viewAction = HomeAction.OpenStoriesDetails(id)
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
                salesHist = homeRepository.fetchSalesHist(),
                advertisingBanner = homeRepository.fetchAdvertisingBanner(),
            )
        }
    }
}