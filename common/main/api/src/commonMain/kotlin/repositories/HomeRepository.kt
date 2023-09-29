package repositories

import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.Stories

interface HomeRepository {

    suspend fun fetchStories(): List<Stories>

    suspend fun fetchStoriesDetails(id: Int): Stories
    suspend fun fetchSalesHist(): List<ResultsItem>

    suspend fun fetchAddCart(productId: Int): Unit
    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner>
}