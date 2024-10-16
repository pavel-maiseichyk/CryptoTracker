package com.paulmais.cryptotracker.crypto.presentation.coin_list

import com.paulmais.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}