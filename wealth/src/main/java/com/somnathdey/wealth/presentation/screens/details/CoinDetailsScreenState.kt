package com.somnathdey.wealth.presentation.screens.details

import com.somnathdey.wealth.domain.model.CoinDetails

data class CoinDetailsScreenState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetails? = null,
    val error: String? = null
)