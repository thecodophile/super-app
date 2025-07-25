package com.somnathdey.wealth.data.repository

import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.data.remote.CoinApi
import com.somnathdey.wealth.data.remote.dto.toCoin
import com.somnathdey.wealth.domain.model.Coin
import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {

            emit(Resource.Loading())

            val coins = api.getCoins()

            emit(Resource.Success(coins.map { it.toCoin() }))

        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach the servers, check your internet connection"))

        }
    }

    override suspend fun getCoinDetails(): Flow<Resource<CoinDetails>> {
        TODO("Not yet implemented")
    }
}