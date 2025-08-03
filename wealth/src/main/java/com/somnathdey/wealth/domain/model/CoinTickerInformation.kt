package com.somnathdey.wealth.domain.model

data class CoinTickerInformation(
    val priceInfo: Map<String, CurrencyInfo>
)