package models.catalog

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val results: List<ResultsItem>?
)