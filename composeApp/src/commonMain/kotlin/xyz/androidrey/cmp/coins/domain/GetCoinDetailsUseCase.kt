package xyz.androidrey.cmp.coins.domain

import xyz.androidrey.cmp.coins.data.mapper.toCoinModel
import xyz.androidrey.cmp.coins.domain.api.CoinsRemoteDataSource
import xyz.androidrey.cmp.coins.domain.model.CoinModel
import xyz.androidrey.cmp.core.domain.DataError
import xyz.androidrey.cmp.core.domain.Result
import xyz.androidrey.cmp.core.domain.map

class GetCoinDetailsUseCase(private val client: CoinsRemoteDataSource) {

    suspend fun execute(coindId: String): Result<CoinModel, DataError.Remote> {
        return client.getCoinById(coindId).map { dto ->
            dto.data.coin.toCoinModel()
        }
    }
}