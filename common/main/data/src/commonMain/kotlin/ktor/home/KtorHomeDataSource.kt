package ktor.home

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import models.catalog.ProductResponse
import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.CartBody
import models.home.Favorite
import models.home.Stories
import settings.SettingsAuthDataSource

class KtorHomeDataSource(
    private val httpClient: HttpClient,
    private val settingsAuthDataSource: SettingsAuthDataSource,
) {

    suspend fun fetchStories(): List<Stories> {
        return httpClient.get {
            url.path("stories/")
        }.body()
    }

    suspend fun fetchStoriesDetails(id: Int): Stories {
        return httpClient.get {
            url.path("stories/$id/")
        }.body()
    }

    suspend fun fetchSalesHits(): List<ResultsItem> {
        val salesHits = arrayListOf<ResultsItem>()
        val productResponse = httpClient.get {
            url.path("products/")
        }.body<ProductResponse>()
        productResponse.results?.map {
            if (it.isHit) {
                salesHits.add(it)
            }
        }
        return salesHits.toList()
    }

    suspend fun fetchAddCart(productId: Int) {
        return httpClient.post {
            url.path("cart-items/")
            bearerAuth(settingsAuthDataSource.fetchAccessToken())
            setBody(CartBody(1, productId, settingsAuthDataSource.userId()))
        }.body()
    }

    suspend fun fetchAddFavorite(productId: Int) {
        return httpClient.post {
            url.path("favorite/")
            bearerAuth(settingsAuthDataSource.fetchAccessToken())
            setBody(Favorite(user = settingsAuthDataSource.userId(), product = productId))
        }.body()
    }

    suspend fun fetchDeleteFavorite(productId: Int) {}

    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner> {
        return httpClient.get {
            url.path("banners/")
        }.body()
    }
}