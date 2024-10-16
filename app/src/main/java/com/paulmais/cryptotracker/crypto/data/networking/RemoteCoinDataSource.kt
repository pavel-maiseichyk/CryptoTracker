package com.paulmais.cryptotracker.crypto.data.networking

import com.paulmais.cryptotracker.core.data.networking.constructUrl
import com.paulmais.cryptotracker.core.data.networking.safeCall
import com.paulmais.cryptotracker.core.domain.util.NetworkError
import com.paulmais.cryptotracker.core.domain.util.Result
import com.paulmais.cryptotracker.core.domain.util.map
import com.paulmais.cryptotracker.crypto.data.mappers.toCoin
import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.paulmais.cryptotracker.crypto.domain.Coin
import com.paulmais.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}