package xyz.androidrey.cmp.coins.data.mapper

import xyz.androidrey.cmp.coins.data.remote.dto.CoinItemDto
import xyz.androidrey.cmp.coins.data.remote.dto.CoinPriceDto
import xyz.androidrey.cmp.coins.domain.model.CoinModel
import xyz.androidrey.cmp.coins.domain.model.PriceModel
import xyz.androidrey.cmp.core.domain.coin.Coin


fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(
        id = uuid,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
    ),
    price = price,
    change = change,
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = price ?: 0.0,
    timestamp = timestamp
)