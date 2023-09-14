package repository

import kotlinx.coroutines.flow.Flow
import utils.Either
import utils.NetworkError

interface HomeRepository {

    suspend fun fetchStories(): Flow<Either<NetworkError, String>>

    suspend fun fetchSalesHits(): Flow<Either<NetworkError, String>>

    suspend fun fetchAdvertisingBanner(): Flow<Either<NetworkError, String>>
}