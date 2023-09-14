package remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import utils.Either
import utils.NetworkError

class KtorHomeDateSource(private val httpClient: HttpClient) {

    suspend fun fetchStories(): Flow<Either<NetworkError, String>> {
        return httpClient.get()
    }

    suspend fun fetchSalesHits(): Flow<Either<NetworkError, String>> {
        return httpClient.get()
    }

    suspend fun fetchAdvertisingBanner(): Flow<Either<NetworkError, String>> {
        return httpClient.get()
    }
}