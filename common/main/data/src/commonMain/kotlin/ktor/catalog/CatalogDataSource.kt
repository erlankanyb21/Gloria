package ktor.catalog

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import models.catalog.CatalogItem

class CatalogDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchCatalog(): CatalogItem{
        return httpClient.get {
            url{
                path("categories")
            }
        }.body()
    }
}