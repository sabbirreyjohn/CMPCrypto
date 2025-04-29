package xyz.androidrey.cmp.coins.domain.api

import xyz.androidrey.cmp.coins.data.remote.dto.CoinDetailsResponseDto
import xyz.androidrey.cmp.coins.data.remote.dto.CoinPriceHistoryResponseDto
import xyz.androidrey.cmp.coins.data.remote.dto.CoinsResponseDto
import xyz.androidrey.cmp.core.domain.DataError
import xyz.androidrey.cmp.core.domain.Result

interface CoinsRemoteDataSource {

    suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote>

    suspend fun getPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote>

    suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote>
}