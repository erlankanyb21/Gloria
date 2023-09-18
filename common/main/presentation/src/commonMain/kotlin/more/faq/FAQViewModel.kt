package more.faq

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import repositories.more.MoreRepository

class FAQViewModel : BaseSharedViewModel<FAQViewState, FAQAction, FAQEvent>(
    initialState = FAQViewState()
) {

    private val moreRepository: MoreRepository = Inject.instance()

    override fun obtainEvent(viewEvent: FAQEvent) {
        when (viewEvent) {
            else -> {}
        }
    }

    init {
        fetchFAQ()
    }

    private fun fetchFAQ() {
        viewModelScope.launch {
            viewState = try {
                val faq = moreRepository.fetchFAQ()
                val whatsApp = moreRepository.openWhatsApp()
                viewState.copy(
                    faqItems = faq.FAQItems,
                    openWhatsApp = whatsApp
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    faqItems = emptyList(),
                    openWhatsApp = null
                )
            }
        }
    }
}