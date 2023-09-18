package repositories

import ktor.home.KtorHomeDataSource
import models.advertisingBanner.AdvertisingBanner
import models.stories.Stories

class HomeRepositoryImpl(private val ktorHomeDataSource: KtorHomeDataSource) : HomeRepository {
    override suspend fun fetchStories(): List<Stories> {
        return ktorHomeDataSource.fetchStories()
    }

    override suspend fun fetchStoriesDetails(id: Int): Stories {
        return ktorHomeDataSource.fetchStoriesDetails(id)
    }

    override suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner> {
        return ktorHomeDataSource.fetchAdvertisingBanner()
    }
}