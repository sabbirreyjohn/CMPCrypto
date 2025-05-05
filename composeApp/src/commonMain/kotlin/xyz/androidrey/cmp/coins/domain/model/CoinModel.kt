package xyz.androidrey.cmp.coins.domain.model

import xyz.androidrey.cmp.core.domain.coin.Coin

data class CoinModel(val coin: Coin,
                     val price: Double,
                     val change: Double,)
