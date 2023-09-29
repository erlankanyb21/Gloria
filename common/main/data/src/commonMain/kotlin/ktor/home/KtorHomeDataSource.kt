package ktor.home

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import models.catalog.ProductResponse
import models.catalog.ResultsItem
import models.home.AdvertisingBanner
import models.home.Stories

class KtorHomeDataSource(private val httpClient: HttpClient) {

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

    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner> {
        return httpClient.get {
            url.path("banners/")
        }.body()
    }
}