package com.somnathdey.wealth.presentation.screens.wealthHome

import com.somnathdey.wealth.domain.model.Coin

data class WealthHomeScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = null
)