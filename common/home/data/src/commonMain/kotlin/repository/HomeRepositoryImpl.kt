package repository

import kotlinx.coroutines.flow.Flow
import remote.KtorHomeDateSource
import utils.Either
import utils.NetworkError

class HomeRepositoryImpl(private val ktorHomeDateSource: KtorHomeDateSource): HomeRepository {
    override suspend fun fetchStories(): Flow<Either<NetworkError, String>> {
        return ktorHomeDateSource.fetchStories()
    }

    override suspend fun fetchSalesHits(): Flow<Either<NetworkError, String>> {
        return ktorHomeDateSource.fetchSalesHits()
    }

    override suspend fun fetchAdvertisingBanner(): Flow<Either<NetworkError, String>> {
        return ktorHomeDateSource.fetchAdvertisingBanner()
    }


}