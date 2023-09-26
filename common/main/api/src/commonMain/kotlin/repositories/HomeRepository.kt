package repositories

import models.advertisingBanner.AdvertisingBanner
import models.favorite.Favorite
import models.stories.Stories

interface HomeRepository {

    suspend fun fetchStories(): List<Stories>

    suspend fun fetchStoriesDetails(id: Int): Stories
    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner>
}