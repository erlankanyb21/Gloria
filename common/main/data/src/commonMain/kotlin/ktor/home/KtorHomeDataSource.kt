package ktor.home

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import models.advertisingBanner.AdvertisingBanner
import models.stories.Stories

class KtorHomeDataSource(private val httpClient: HttpClient) {

    suspend fun fetchStories(): List<Stories> {
        return httpClient.get {
            url.path("stories")
        }.body()
    }

    suspend fun fetchStoriesDetails(id: Int): Stories {
        return httpClient.get {
            url.path("stories/$id")
        }.body()

    }

    suspend fun fetchAdvertisingBanner(): List<AdvertisingBanner> {
        return httpClient.get {
            url.path("banners")
        }.body()
    }
}