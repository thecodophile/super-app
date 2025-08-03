package com.somnathdey.wealth.domain.repository

import com.somnathdey.datasource.local.entity.Coin
import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.domain.model.CoinTickerInformation
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetails>>

    suspend fun getCoinTickerInformation(coinId: String): Flow<Resource<CoinTickerInformation>>
}