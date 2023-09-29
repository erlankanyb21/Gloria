package more.favorite

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import repositories.more.MoreRepository

class FavoriteViewModel : BaseSharedViewModel<FavoriteViewState, FavoriteAction, FavoriteEvent>(
    initialState = FavoriteViewState()
) {
    private val moreRepository: MoreRepository = Inject.instance()
    override fun obtainEvent(viewEvent: FavoriteEvent) {
        TODO("Not yet implemented")
    }

    init {
        withViewModelScope {
            if (moreRepository.fetchFavorite().isNotEmpty()) {
                viewState = viewState.copy(favorite = moreRepository.fetchFavorite())
            }
        }
    }
}