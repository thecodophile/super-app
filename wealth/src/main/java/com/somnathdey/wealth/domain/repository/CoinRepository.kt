package com.somnathdey.wealth.domain.repository

import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.model.Coin
import com.somnathdey.wealth.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetails>>
}