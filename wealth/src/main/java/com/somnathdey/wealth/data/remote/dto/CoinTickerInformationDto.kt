package com.somnathdey.wealth.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.somnathdey.wealth.domain.model.CoinTickerInformation

data class CoinTickerInformationDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("total_supply")
    val totalSupply: Long,
    @SerializedName("max_supply")
    val maxSupply: Long,
    @SerializedName("beta_value")
    val betaValue: Double,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("quotes")
    val priceInfo: Map<String, CurrencyInfoDto>
)

fun CoinTickerInformationDto.toCoinTickerInformation(): CoinTickerInformation{
    return CoinTickerInformation(
        priceInfo = priceInfo.mapValues { it.value.toCurrencyInfo() }
    )
}