package com.paulmais.cryptotracker.crypto.data.networking

import com.paulmais.cryptotracker.core.data.networking.constructUrl
import com.paulmais.cryptotracker.core.data.networking.safeCall
import com.paulmais.cryptotracker.core.domain.util.NetworkError
import com.paulmais.cryptotracker.core.domain.util.Result
import com.paulmais.cryptotracker.core.domain.util.map
import com.paulmais.cryptotracker.crypto.data.mappers.toCoin
import com.paulmais.cryptotracker.crypto.data.mappers.toCoinPrice
import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.paulmais.cryptotracker.crypto.domain.Coin
import com.paulmais.cryptotracker.crypto.domain.CoinDataSource
import com.paulmais.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinHistory(
        coinID: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        return safeCall<CoinHistoryDto> {
            val startMillis = start
                .withZoneSameInstant(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli()

            val endMillis = end
                .withZoneSameInstant(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli()

            httpClient.get(
                urlString = constructUrl("/assets/$coinID/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}