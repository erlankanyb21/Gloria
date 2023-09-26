package ktor.product

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import models.catalog.ProductResponse
import models.catalog.ResultsItem

class KtorProductDataSource(
    private val httpsClient: HttpClient
) {

    suspend fun fetchProduct(): ProductResponse {
        return httpsClient.get { url.path("products") }.body()
    }
}