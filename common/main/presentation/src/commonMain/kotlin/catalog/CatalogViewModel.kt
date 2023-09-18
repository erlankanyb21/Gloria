package catalog

import catalog.models.CatalogAction
import catalog.models.CatalogEvent
import catalog.models.CatalogViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.CatalogRepository

class CatalogViewModel : BaseSharedViewModel<CatalogViewState, CatalogAction, CatalogEvent>(
    initialState = CatalogViewState()
) {
    private val catalogRepository: CatalogRepository = Inject.instance()
    override fun obtainEvent(viewEvent: CatalogEvent) {

    }

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            viewState = try {
                val response = catalogRepository.fetchCatalog()
                viewState.copy(
                    catalogItem = response
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    catalogItem = emptyList()
                )
            }
        }
    }

    fun getSubCatalog(slug: String) {
        viewModelScope.launch {
            viewState = try {
                val response = catalogRepository.fetchSubCatalog(slug)
                viewState.copy(
                    subCatalogItem = response
                )
            } catch (e: Exception) {
                viewState
            }
        }
    }
}