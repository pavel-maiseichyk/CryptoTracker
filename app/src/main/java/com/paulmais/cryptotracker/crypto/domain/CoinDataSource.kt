package com.paulmais.cryptotracker.crypto.domain

import com.paulmais.cryptotracker.core.domain.util.NetworkError
import com.paulmais.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}