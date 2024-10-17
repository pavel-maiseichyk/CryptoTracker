package com.paulmais.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    @SerialName("priceUsd") val priceUSD: Double,
    val time: Long
)
