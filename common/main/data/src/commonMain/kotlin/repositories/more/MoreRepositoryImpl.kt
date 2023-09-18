package repositories.more

import ktor.more.MoreDataSource
import models.more.faq.FAQResponse
import models.more.faq.WhatsAppResponse
import models.more.profile.GetProfileResponse
import models.more.profile.UpdateProfileBody

class MoreRepositoryImpl(
    private val moreDataSource: MoreDataSource
): MoreRepository {
    override suspend fun getProfile(): GetProfileResponse {
        return moreDataSource.getProfile()
    }

    override suspend fun fetchFAQ(): FAQResponse {
        return moreDataSource.fetchFAQ()
    }

    override suspend fun openWhatsApp(): WhatsAppResponse {
        return moreDataSource.openWhatsApp()
    }

    override suspend fun updateProfile(updateProfileBody: UpdateProfileBody): GetProfileResponse {
        return moreDataSource.updateProfile(updateProfileBody)
    }

    override suspend fun uploadImage(editImage: ByteArray?): GetProfileResponse {
        return moreDataSource.editImage(editImage)
    }
}