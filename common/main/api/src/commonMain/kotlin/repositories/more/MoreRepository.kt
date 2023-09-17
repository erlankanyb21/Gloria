package repositories.more

import models.more.faq.FAQResponse
import models.more.faq.WhatsAppResponse
import models.more.profile.GetProfileResponse
import models.more.profile.UpdateProfileBody

interface MoreRepository {

    suspend fun getProfile(): GetProfileResponse

    suspend fun fetchFAQ(): FAQResponse

    suspend fun openWhatsApp(): WhatsAppResponse

    suspend fun updateProfile(updateProfileBody: UpdateProfileBody): GetProfileResponse

    suspend fun uploadImage(editImage:ByteArray): GetProfileResponse
}