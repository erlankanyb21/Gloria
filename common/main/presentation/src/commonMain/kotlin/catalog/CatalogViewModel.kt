package catalog

import catalog.models.CatalogAction
import catalog.models.CatalogEvent
import catalog.models.CatalogViewState
import com.adeo.kviewmodel.BaseSharedViewModel

class CatalogViewModel : BaseSharedViewModel<CatalogViewState,CatalogAction ,CatalogEvent>(
    initialState = CatalogViewState()
) {
    override fun obtainEvent(viewEvent: CatalogEvent) {

    }
}