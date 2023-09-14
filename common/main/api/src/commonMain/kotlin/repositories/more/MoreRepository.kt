package repositories.more

import models.more.faq.FAQResponse
import models.more.faq.WhatsAppResponse

interface MoreRepository {

    suspend fun fetchFAQ(): FAQResponse

    suspend fun openWhatsApp(): WhatsAppResponse
}