package com.somnathdey.wealth.domain.model

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean
)
