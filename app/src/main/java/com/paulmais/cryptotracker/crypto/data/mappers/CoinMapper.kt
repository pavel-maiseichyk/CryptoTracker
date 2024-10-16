package com.paulmais.cryptotracker.crypto.data.mappers

import com.paulmais.cryptotracker.crypto.data.networking.dto.CoinDto
import com.paulmais.cryptotracker.crypto.domain.Coin

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