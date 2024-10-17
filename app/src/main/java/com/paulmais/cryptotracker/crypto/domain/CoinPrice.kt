package com.paulmais.cryptotracker.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUSD: Double,
    val dateTime: ZonedDateTime
)
