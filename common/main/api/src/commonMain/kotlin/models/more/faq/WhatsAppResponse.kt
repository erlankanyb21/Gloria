package models.more.faq

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WhatsAppResponse(
    @SerialName("whatsapp_url")
    val whatsappUrl: String
)