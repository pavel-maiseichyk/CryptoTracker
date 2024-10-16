package com.paulmais.cryptotracker.crypto.presentation.coin_list

import com.paulmais.cryptotracker.crypto.presentation.models.CoinUI

sealed interface CoinListAction {
    data class OnCoinClick(val coinUI: CoinUI): CoinListAction
}