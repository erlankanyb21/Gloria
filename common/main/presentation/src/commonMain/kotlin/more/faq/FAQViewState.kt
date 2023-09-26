package more.faq

import models.more.faq.FAQItems
import models.more.faq.WhatsAppResponse

data class FAQViewState(
    val faqItems: List<FAQItems> = emptyList(),
    val openWhatsApp: WhatsAppResponse? = null,
)