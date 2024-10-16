package com.paulmais.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    @SerialName("marketCapUsd") val marketCapUSD: Double,
    @SerialName("priceUsd") val priceUSD: Double,
    val changePercent24Hr: Double
)
