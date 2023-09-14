package repositories.more

import ktor.more.MoreDataSource
import models.more.faq.FAQResponse
import models.more.faq.WhatsAppResponse

class MoreRepositoryImpl(
    private val moreDataSource: MoreDataSource
): MoreRepository {

    override suspend fun fetchFAQ(): FAQResponse {
        return moreDataSource.fetchFAQ()
    }

    override suspend fun openWhatsApp(): WhatsAppResponse {
        return moreDataSource.openWhatsApp()
    }

}