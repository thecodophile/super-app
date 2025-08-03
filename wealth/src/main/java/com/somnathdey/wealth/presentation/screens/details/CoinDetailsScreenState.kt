package com.somnathdey.wealth.presentation.screens.details

import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.domain.model.CoinTickerInformation

data class CoinDetailsScreenState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetails? = null,
    val error: String? = null,

    val isLoadingCoinTickerInformation: Boolean = false,
    val coinTickerInformation: CoinTickerInformation? = null,
    val errorFetchingCoinTickerInformation: String? = null
)