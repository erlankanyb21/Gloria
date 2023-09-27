package catalog

import catalog.models.CatalogAction
import catalog.models.CatalogEvent
import catalog.models.CatalogViewState
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.CatalogRepository
import repositories.ProductRepository

class CatalogViewModel : BaseSharedViewModel<CatalogViewState, CatalogAction, CatalogEvent>(
    initialState = CatalogViewState()
) {
    private val catalogRepository: CatalogRepository = Inject.instance()
    private val productRepository: ProductRepository = Inject.instance()
    override fun obtainEvent(viewEvent: CatalogEvent) {
        when (viewEvent){
            is CatalogEvent.OpenSubCatalogClick -> { openSubCatalog(viewEvent.slag) }
            is CatalogEvent.OpenProductClick -> { openProduct() }
            is CatalogEvent.OnBackClick -> { onBackClick() }
        }
    }

    init {
        fetchProduct()
        getProduct()
    }

    private fun openSubCatalog(slag: String) {
        viewAction = CatalogAction.OpenSubCatalog(slag)
    }
    private fun onBackClick() {
        viewAction = CatalogAction.OnBackClick
    }


    private fun openProduct() {
        viewAction = CatalogAction.OpenProduct
    }



    private fun fetchProduct() {
        viewModelScope.launch {
            viewState = try {
                val response = catalogRepository.fetchCatalog()
                viewState.copy(
                    catalogItem = response, loading = false
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
                    subCatalogItem = response, loading = false
                )
            } catch (e: Exception) {
                viewState
            }
        }
    }

    fun getProduct() {
        viewModelScope.launch {
            viewState = try {
                val response = productRepository.fetchProduct()
                viewState.copy(
                    productItem = response , loading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState
            }
        }
    }
}