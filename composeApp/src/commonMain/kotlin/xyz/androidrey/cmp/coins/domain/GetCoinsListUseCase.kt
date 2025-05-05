package xyz.androidrey.cmp.coins.domain

import xyz.androidrey.cmp.coins.domain.api.CoinsRemoteDataSource
import xyz.androidrey.cmp.core.domain.map
import xyz.androidrey.cmp.core.domain.Result
import xyz.androidrey.cmp.coins.data.mapper.toCoinModel
import xyz.androidrey.cmp.coins.domain.model.CoinModel
import xyz.androidrey.cmp.core.domain.DataError

class GetCoinsListUseCase(private val client: CoinsRemoteDataSource) {

    suspend fun execute(): Result<List<CoinModel>, DataError.Remote> {
        return client.getListOfCoins().map { dto ->
            dto.data.coins.map { it.toCoinModel() }
        }
    }
}