package storiesDetails

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import repositories.HomeRepository
import storiesDetails.models.StoriesDetailsAction
import storiesDetails.models.StoriesDetailsEvent
import storiesDetails.models.StoriesDetailsViewState

class StoriesDetailsViewModel :
    BaseSharedViewModel<StoriesDetailsViewState, StoriesDetailsAction, StoriesDetailsEvent>(
        StoriesDetailsViewState(storiesDetails = "")
    ) {
    private val homeRepository: HomeRepository = Inject.instance()
    override fun obtainEvent(viewEvent: StoriesDetailsEvent) {
        when (viewEvent) {
            is StoriesDetailsEvent.DownloadStories -> downLoadStories(viewEvent.id)
        }
    }

    private fun downLoadStories(id: Int) {
        withViewModelScope {
            viewState =
                viewState.copy(storiesDetails = homeRepository.fetchStoriesDetails(id).image)
        }
    }
}