package com.paulmais.cryptotracker.crypto.data.mappers

import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinDto
import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.paulmais.cryptotracker.crypto.domain.Coin
import com.paulmais.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUSD = marketCapUSD,
        changePercent24Hr = changePercent24Hr,
        priceUSD = priceUSD
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUSD = priceUSD,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}