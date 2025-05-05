package xyz.androidrey.cmp.coins.domain

import xyz.androidrey.cmp.coins.data.mapper.toPriceModel
import xyz.androidrey.cmp.coins.domain.api.CoinsRemoteDataSource
import xyz.androidrey.cmp.coins.domain.model.PriceModel
import xyz.androidrey.cmp.core.domain.DataError
import xyz.androidrey.cmp.core.domain.Result
import xyz.androidrey.cmp.core.domain.map

class GetCoinPriceHistoryUseCase(private val client: CoinsRemoteDataSource) {
    suspend fun execute(coindId: String): Result<List<PriceModel>, DataError.Remote> {
        return client.getPriceHistory(coindId).map { dto ->
            dto.data.history.map { it.toPriceModel() }
        }
    }
}