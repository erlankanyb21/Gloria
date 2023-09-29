package home.models

import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.Stories

data class HomeViewState(
    var stories: List<Stories> = emptyList(),
    val storiesDetails: String,
    val salesHist: List<ResultsItem> = emptyList(),
    val advertisingBanner: List<AdvertisingBanner> = emptyList(),
)
