package com.somnathdey.wealth.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.somnathdey.wealth.domain.model.CurrencyInfo

data class CurrencyInfoDto(
    val price: Double,
    @SerializedName("volume_24h")
    val volume24h: Double,
    @SerializedName("volume_24h_change_24h")
    val volume24hChange24h: Double,
    @SerializedName("market_cap")
    val marketCap: Long,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerializedName("percent_change_15m")
    val percentChange15m: Double,
    @SerializedName("percent_change_30m")
    val percentChange30m: Double,
    @SerializedName("percent_change_1h")
    val percentChange1h: Double,
    @SerializedName("percent_change_6h")
    val percentChange6h: Double,
    @SerializedName("percent_change_12h")
    val percentChange12h: Double,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double,
    @SerializedName("percent_change_7d")
    val percentChange7d: Double,
    @SerializedName("percent_change_30d")
    val percentChange30d: Double,
    @SerializedName("percent_change_1y")
    val percentChange1y: Double,
    @SerializedName("ath_price")
    val athPrice: Double,
    @SerializedName("ath_date")
    val athDate: String,
    @SerializedName("percent_from_price_ath")
    val percentFromPriceAth: Double
)

fun CurrencyInfoDto.toCurrencyInfo(): CurrencyInfo {
    return CurrencyInfo(
        price = price
    )
}