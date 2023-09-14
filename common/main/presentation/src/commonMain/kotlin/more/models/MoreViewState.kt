package more.models

import models.more.faq.FAQItems
import models.more.faq.WhatsAppResponse

data class MoreViewState(
    val faqItems: List<FAQItems> = emptyList(),
    val openWhatsApp: WhatsAppResponse? = null
)