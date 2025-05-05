package xyz.androidrey.cmp.coins.data.remote.impl

import io.ktor.client.HttpClient
import xyz.androidrey.cmp.coins.data.remote.dto.CoinDetailsResponseDto
import xyz.androidrey.cmp.coins.data.remote.dto.CoinPriceHistoryResponseDto
import xyz.androidrey.cmp.coins.data.remote.dto.CoinsResponseDto
import xyz.androidrey.cmp.coins.domain.api.CoinsRemoteDataSource
import xyz.androidrey.cmp.core.domain.DataError
import xyz.androidrey.cmp.core.network.safeCall
import io.ktor.client.request.get
import xyz.androidrey.cmp.core.domain.Result

private const val BASE_URL = "https://api.coinranking.com/v2"

class KtorCoinsRemoteDataSource(
    private val httpClient: HttpClient
) : CoinsRemoteDataSource {

    override suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coins")
        }
    }

    override suspend fun getPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId/history")
        }
    }

    override suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId")
        }
    }
}