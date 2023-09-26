package home.models

import models.advertisingBanner.AdvertisingBanner
import models.favorite.Favorite
import models.stories.Stories

data class HomeViewState(
    var stories: List<Stories> = emptyList(),
    val storiesDetails: String,
    val salesHits: List<Favorite> = emptyList(),
    val advertisingBanner: List<AdvertisingBanner> = emptyList()
)
