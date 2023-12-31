package repositories

import ktor.home.KtorHomeDataSource
import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.Stories

class HomeRepositoryImpl(private val ktorHomeDataSource: KtorHomeDataSource) : HomeRepository {
    override suspend fun fetchStories(): List<Stories> {
        return ktorHomeDataSource.fetchStories()
    }

    override suspend fun fetchStoriesDetails(id: Int): Stories {
        return ktorHomeDataSource.fetchStoriesDetails(id)
    }

    override suspend fun fetchSalesHist(): List<ResultsItem> {
        return ktorHomeDataSource.fetchSalesHits()
    }

    override suspend fun fetchAddFavorite(productId: Int) {
        ktorHomeDataSource.fetchAddFavorite(productId)
    }

    override suspend fun fetchDeleteFavorite(productId: Int) {
        ktorHomeDataSource
    }

    override suspend fun fetchAddCart(productId: Int) {
        ktorHomeDataSource.fetchAddCart(productId)
    }


    override suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner> {
        return ktorHomeDataSource.fetchAdvertisingBanner()
    }
}