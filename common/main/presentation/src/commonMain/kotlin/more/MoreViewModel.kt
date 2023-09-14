package more

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import models.more.faq.WhatsAppResponse
import more.models.MoreAction
import more.models.MoreEvent
import more.models.MoreViewState
import repositories.more.MoreRepository

class MoreViewModel : BaseSharedViewModel<MoreViewState, MoreAction, MoreEvent>(
    initialState = MoreViewState()
) {

    private val moreRepository: MoreRepository = Inject.instance()

    override fun obtainEvent(viewEvent: MoreEvent) {
        when (viewEvent) {
            MoreEvent.OpenWhatsApp -> openWhatsApp()
        }
    }

    init {
        fetchFAQ()
        openWhatsApp()
    }

    private fun fetchFAQ() {
        viewModelScope.launch {
            viewState = try {
                val response = moreRepository.fetchFAQ()
                viewState.copy(
                    faqItems = response.FAQItems
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    faqItems = emptyList()
                )
            }
        }
    }

    private fun openWhatsApp() {
        viewModelScope.launch {
            viewState = try {
                val response = moreRepository.openWhatsApp()
                viewState.copy(
                    openWhatsApp = response
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    openWhatsApp = WhatsAppResponse("")
                )
            }
        }
    }
}