package xyz.androidrey.cmp.coins.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import xyz.androidrey.cmp.coins.domain.GetCoinsListUseCase
import xyz.androidrey.cmp.core.domain.Result
import xyz.androidrey.cmp.core.util.formatFiat
import xyz.androidrey.cmp.core.util.formatPercentage

class CoinListViewModel(private val getCoinsListUseCase: GetCoinsListUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CoinsState())
    val state = _state
        .onStart {
            getAllCoins()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CoinsState()
        )

    private suspend fun getAllCoins() {
        when (val coinsResponse = getCoinsListUseCase.execute()) {
            is Result.Success -> {
                _state.update {
                    CoinsState(
                        coins = coinsResponse.data.map { coinItem ->
                            UiCoinListItem(
                                id = coinItem.coin.id,
                                name = coinItem.coin.name,
                                iconUrl = coinItem.coin.iconUrl,
                                symbol = coinItem.coin.symbol,
                                formattedPrice = formatFiat(coinItem.price),
                                formattedChange = formatPercentage(coinItem.change),
                                isPositive = coinItem.change >= 0,
                            )
                        }
                    )
                }
            }

            is Result.Error -> {
                _state.update {
                    it.copy(
                        coins = emptyList(),
                        error = null //TODO: coinsResponse.error.toUiText()
                    )
                }
            }
        }
    }
}