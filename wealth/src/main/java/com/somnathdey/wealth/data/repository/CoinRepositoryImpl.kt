package com.somnathdey.wealth.data.repository

import com.somnathdey.datasource.local.LocalDataSource
import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.data.remote.CoinApi
import com.somnathdey.wealth.data.remote.dto.toCoin
import com.somnathdey.wealth.data.remote.dto.toCoinDetails
import com.somnathdey.datasource.local.entity.Coin
import com.somnathdey.utilities.logging.AppLogger
import com.somnathdey.wealth.data.remote.dto.toCoinTickerInformation
import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.domain.model.CoinTickerInformation
import com.somnathdey.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi,
    private val localDataSource: LocalDataSource
) : CoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {

            emit(Resource.Loading())

            val coinListFromDB = getCoinListFromDatabase()

            coinListFromDB?.also {
                emit(Resource.Success(it))
                AppLogger.d(message = "Success getCoinListFromDatabase with size ${coinListFromDB.size}")
            }

            val coins = api.getCoins()

            insertCoinListInDatabase(coins.map { it.toCoin() })

            emit(Resource.Success(coins.map { it.toCoin() }))

        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach the servers, check your internet connection"))

        }
    }

    override suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {

            emit(Resource.Loading())

            val coinsDetails = api.getCoinDetails(coinId = coinId)

            emit(Resource.Success(coinsDetails.toCoinDetails()))

        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach the servers, check your internet connection"))

        }
    }

    override suspend fun getCoinTickerInformation(coinId: String): Flow<Resource<CoinTickerInformation>> =
        flow {
            try {
                emit(Resource.Loading())

                val coinTickerInformation = api.getCoinTickerInformation(coinId = coinId)

                emit(Resource.Success(coinTickerInformation.toCoinTickerInformation()))

            } catch (e: HttpException) {

                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

            } catch (e: IOException) {

                emit(Resource.Error("Couldn't reach the servers, check your internet connection"))

            }
        }

    private suspend fun insertCoinListInDatabase(coinList: List<Coin>) {
        withContext(Dispatchers.IO) {
            try {
                localDataSource.insertCoinListInDatabase(coinList)
                AppLogger.d(message = "Success insertCoinListInDatabase with size ${coinList.size}")
            } catch (error: Exception) {
                AppLogger.d(message = "Error insertCoinListInDatabase-> $error")
            }
        }
    }

    private suspend fun getCoinListFromDatabase(): List<Coin>? =
        withContext(Dispatchers.IO) {
            val coinList = localDataSource.getCoinListFromDB()
            coinList.ifEmpty {
                null
            }
        }

}