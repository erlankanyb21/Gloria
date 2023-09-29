package repositories

import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.Stories

interface HomeRepository {

    suspend fun fetchStories(): List<Stories>

    suspend fun fetchStoriesDetails(id: Int): Stories
    suspend fun fetchSalesHist(): List<ResultsItem>
    suspend fun fetchAddFavorite(productId: Int)
    suspend fun fetchDeleteFavorite(productId: Int)

    suspend fun fetchAddCart(productId: Int)
    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner>
}