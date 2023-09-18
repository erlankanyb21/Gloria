package ktor.catalog

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import models.catalog.CatalogItem

class KtorCatalogDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchCatalog(): List<CatalogItem> {
        return httpClient.get { url.path("categories") }.body()
    }

    suspend fun fetchSubCatalog(slug: String): CatalogItem {
        return httpClient.get { url.path("categories/$slug") }.body()
    }
}