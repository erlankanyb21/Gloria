package models.more.faq

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FAQResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val FAQItems: List<FAQItems>
)